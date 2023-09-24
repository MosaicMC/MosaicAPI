package io.github.mosaicmc.mosaicapi.impl;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.github.mosaicmc.mosaicapi.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.PluginInitializer;
import io.github.mosaicmc.mosaicapi.api.PluginManager;
import io.github.mosaicmc.mosaicapi.api.mc.Server;
import io.github.mosaicmc.mosaicapi.util.BiIterable;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PluginManagerImpl implements PluginManager {
    final BiMap<String, PluginContainer> plugins = HashBiMap.create(new HashMap<>());

    public void loadPlugins(Server server) {
        final var initializers = FabricLoader.getInstance()
                .getEntrypointContainers("plugin", PluginInitializer.class);
        toPluginsBuilder(initializers).forEach((k, v) -> this.addPlugin(server, k, v));
    }

    private void addPlugin(Server server, String id, List<PluginInitializer> initializers) {
        PluginContainer container = new PluginContainerImpl(initializers, server, id);
        this.plugins.put(id, container);
    }

    private Map<String, List<PluginInitializer>> toPluginsBuilder(List<EntrypointContainer<PluginInitializer>> initializers) {
        final var pluginsMap = new HashMap<String, List<PluginInitializer>>();

        for (final var initializer : initializers) {
            String id = initializer.getProvider().getMetadata().getId();
            pluginsMap.computeIfAbsent(id, k -> new ArrayList<>())
                    .add(initializer.getEntrypoint());
        }

        return pluginsMap;
    }


    @Override
    public BiIterable<String, PluginContainer> getPlugins() {
        return BiIterable.of(this.plugins);
    }
}
