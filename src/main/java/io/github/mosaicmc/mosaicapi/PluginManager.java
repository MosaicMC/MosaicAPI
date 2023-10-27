package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.server.MinecraftServer;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PluginManager {
    private final MinecraftServer server;
    private final FabricLoader fabricLoader;
    private final Init<BiMap<String, PluginContainer>> plugins = new Init<>();

    PluginManager() {
        this.fabricLoader = FabricLoader.getInstance();
        this.server = Loader.getInstance().getServer();
    }

    private String getId(EntrypointContainer<PluginEntrypoint> entry) {
        return entry.getProvider().getMetadata().getId();
    }

    private BiMap<String, PluginContainer> collectImmutableMap(Map<String, ImmutableList.Builder<PluginEntrypoint>> map) {
        final var builder = ImmutableBiMap.<String, PluginContainer>builder();
        for (final var entry : map.entrySet()) {
            builder.put(entry.getKey(), new PluginContainer(
                    entry.getKey(),
                    entry.getValue().build(),
                    LoggerFactory.getLogger(entry.getKey())
            ));
        }
        return builder.buildOrThrow();
    }

    private Map<String, ImmutableList.Builder<PluginEntrypoint>> toMap(List<EntrypointContainer<PluginEntrypoint>> list) {
        final var map = new HashMap<String, ImmutableList.Builder<PluginEntrypoint>>();

        for (final var container : list) {
            final var id = getId(container);
            map.computeIfAbsent(
                    id,
                    unused -> ImmutableList.builder()
            ).add(container.getEntrypoint());
        }

        return map;
    }


    public void preloadPlugins() {
        final var entries = fabricLoader.getEntrypointContainers("plugin", PluginEntrypoint.class);
        this.plugins.init(() -> collectImmutableMap(toMap(entries)));
    }

    public BiMap<String, PluginContainer> getPlugins() {
        return plugins.get();
    }
}
