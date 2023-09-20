package io.github.mosaicmc.mosaicapi.impl.loader;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import io.github.mosaicmc.mosaicapi.api.event.Event;
import io.github.mosaicmc.mosaicapi.api.event.EventHandler;
import io.github.mosaicmc.mosaicapi.api.loader.Loader;
import io.github.mosaicmc.mosaicapi.api.loader.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.loader.PluginInitializer;
import io.github.mosaicmc.mosaicapi.api.mc.MosaicServer;
import io.github.mosaicmc.mosaicapi.impl.event.EventHandlerImpl;
import net.fabricmc.loader.api.FabricLoader;

import java.util.HashMap;

public final class LoaderImpl implements Loader {
    private final MosaicServer server;
    private final BiMap<String, PluginContainer> pluginsCache;
    private final EventHandler eventHandler;
    public static Loader INSTANCE = null;

    public LoaderImpl(MosaicServer server) {
        this.server = server;
        this.pluginsCache = createPluginsMap();
        this.eventHandler = new EventHandlerImpl();
        loadPlugins();
        INSTANCE = this;
    }

    @Override
    public void unloadPlugin(String id) {
        pluginsCache.get(id).getInitializers().forEach(PluginInitializer::unload);
        pluginsCache.remove(id);
    }

    @Override
    public <T extends Event<T>> void fireEvent(T event) {
        eventHandler.fireEvent(event);
    }

    private BiMap<String, PluginContainer> createPluginsMap() {
        final var entrypointContainers =
                FabricLoader.getInstance().getEntrypointContainers("plugin", PluginInitializer.class);
        final var plugins = new HashMap<String, PluginContainer>();

        entrypointContainers.forEach(entry -> {
            final var id = entry.getProvider().getMetadata().getId();
            plugins.computeIfAbsent(id, key -> {
                final var builder = PluginContainer.builder(id, server);
                builder.add(entry.getEntrypoint());
                return builder.build();
            });
        });

        return Maps.synchronizedBiMap(HashBiMap.create(plugins));
    }

    private void loadPlugins() {
        final var eventRegistryBuilder = eventHandler.eventRegistryBuilder();
        final var listenerBuilder = eventHandler.listenerBuilder();
        pluginsCache.forEach(($, plugin) -> {
            for (final var initializer : plugin.getInitializers()) {
                initializer.registerEvents(plugin, eventRegistryBuilder);
            }
        });
        eventHandler.registerEventRegistry(eventRegistryBuilder);
        pluginsCache.forEach(($, plugin) -> {
            for (final var initializer : plugin.getInitializers()) {
                initializer.init(plugin, listenerBuilder);
            }
        });
        eventHandler.registerListener(listenerBuilder);
    }
}
