package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import io.github.mosaicmc.mosaicapi.utils.Init;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

@ApiStatus.Internal
public final class PluginManager {
    private final FabricLoader fabricLoader;
    private final Init<BiMap<String, PluginContainer>> plugins = new Init<>();

    PluginManager() {
        this.fabricLoader = FabricLoader.getInstance();
    }

    private BiMap<String, PluginContainer> collectImmutableMap(List<EntrypointContainer<PluginEntrypoint>> list) {
        final var builder = new HashMap<String, PluginContainer>();
        for (final var entry : list) {
            final var name = entry.getProvider().getMetadata().getId();
            if (builder.containsKey(name))
                throw new IllegalStateException("Multiplicative initialization points not allowed. Plugin: " + name);
            final var container = new PluginContainer(
                    name,
                    entry.getEntrypoint(),
                    LoggerFactory.getLogger(name)
            );
            builder.put(name, container);
        }
        return ImmutableBiMap.copyOf(builder);
    }

    public void preloadPlugins() {
        final var entries = fabricLoader.getEntrypointContainers("plugin", PluginEntrypoint.class);
        this.plugins.init(collectImmutableMap(entries));
    }

    public void loadPlugins() {
        plugins.get().values().parallelStream().forEach((plugin) -> {
            final var entrypoint = plugin.entrypoint();
            entrypoint.plugin.init(plugin);
            entrypoint.load();
        });
    }

    public BiMap<String, PluginContainer> getPlugins() {
        return plugins.get();
    }
}
