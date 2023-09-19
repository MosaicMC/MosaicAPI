package io.github.mosaicmc.mosaicapi.loader;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import io.github.mosaicmc.mosaicapi.event.Event;
import io.github.mosaicmc.mosaicapi.mc.MosaicServer;
import java.util.HashMap;
import net.fabricmc.loader.api.FabricLoader;

public final class LoaderImpl extends Loader {
    LoaderImpl(MosaicServer server) {
        super(server);
    }

    @Override
    public void unloadPlugin(String id) {
        pluginsCache.get(id).initializers.forEach(PluginInitializer::unload);
        pluginsCache.remove(id);
    }

    @Override
    protected BiMap<String, PluginContainer> createPluginsMap() {
        final var entrypointContainers =
                FabricLoader.getInstance().getEntrypointContainers("plugin", PluginInitializer.class);
        final var plugins = new HashMap<String, PluginContainer>();

        entrypointContainers.forEach(entry -> {
            final var id = entry.getProvider().getMetadata().getId();
            plugins.computeIfAbsent(id, key -> {
                final var builder = PluginContainer.builder(id, server, this);
                builder.add(entry.getEntrypoint());
                return builder.build();
            });
        });

        return Maps.synchronizedBiMap(HashBiMap.create(plugins));
    }

    @Override
    protected void loadPlugins() {
        final var eventRegistryBuilder = eventHandler.eventRegistryBuilder();
        final var listenerBuilder = eventHandler.listenerBuilder();
        pluginsCache.forEach(($, plugin) -> {
            for (final var init : plugin.initializers) {
                init.registerEvents(plugin, eventRegistryBuilder);
            }
        });
        eventHandler.registerEventRegistry(eventRegistryBuilder);
        pluginsCache.forEach(($, plugin) -> {
            for (final var init : plugin.initializers) {
                init.init(plugin, listenerBuilder);
            }
        });
        eventHandler.registerListener(listenerBuilder);
    }

    @Override
    public <T extends Event<T>> void fireEvent(T event) {
        eventHandler.fireEvent(event);
    }
}
