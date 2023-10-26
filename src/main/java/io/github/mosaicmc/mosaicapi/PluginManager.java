package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class PluginManager {
    private final MinecraftServer server;
    private final FabricLoader fabricLoader;
    private final BiMap<String, PluginContainer> plugins;

    PluginManager(MinecraftServer server) {
        this.server = server;
        this.fabricLoader = FabricLoader.getInstance();
        this.plugins = loadPlugins();
    }

    private String getId(@NotNull EntrypointContainer<PluginEntrypoint> entry) {
        return entry.getProvider().getMetadata().getId();
    }

    private ImmutableBiMap<String, PluginContainer> collectImmutableMap(@NotNull Map<String, List<PluginEntrypoint>> map) {
        return map.entrySet()
                .stream()
                .collect(ImmutableBiMap.toImmutableBiMap(
                        Map.Entry::getKey,
                        entry -> new PluginContainer(entry.getKey(), ImmutableList.copyOf(entry.getValue()))
                ));
    }

    private Collector<EntrypointContainer<PluginEntrypoint>, ?, Map<String, List<PluginEntrypoint>>> toMapCollector() {
        return Collectors.groupingBy(this::getId,
                Collectors.mapping(
                        EntrypointContainer::getEntrypoint,
                        Collectors.toList()
                )
        );
    }


    private BiMap<String, PluginContainer> loadPlugins() {
        final var entries = fabricLoader.getEntrypointContainers("plugin", PluginEntrypoint.class);
        final var plugins =  entries.stream().collect(Collectors.collectingAndThen(
                this.toMapCollector(),
                this::collectImmutableMap
        ));
        Loader.getInstance().logger.info("Loaded " + plugins.size() + " plugins");
        return plugins;
    }


}
