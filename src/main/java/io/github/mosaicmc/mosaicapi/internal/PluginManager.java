package io.github.mosaicmc.mosaicapi.internal;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import io.github.mosaicmc.mosaicapi.api.IPluginManager;
import io.github.mosaicmc.mosaicapi.api.PluginEntrypoint;
import io.github.mosaicmc.mosaicapi.utils.InitHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApiStatus.Internal
public final class PluginManager implements IPluginManager {
    private final FabricLoader fabricLoader;
    private final InitHelper<BiMap<String, PluginContainer>> pluginMapInitHelper;
    private final InitHelper<EventManager> eventManagerInitHelper;

    private PluginManager() {
        this.fabricLoader = FabricLoader.getInstance();
        this.pluginMapInitHelper = new InitHelper<>();
        this.eventManagerInitHelper = new InitHelper<>();
    }

    public static PluginManager initialize() {
        PluginManager manager = new PluginManager();
        manager.generatePlugins();
        manager.loadPlugins();
        return manager;
    }

    private BiMap<String, PluginContainer> collectPluginMap(List<EntrypointContainer<PluginEntrypoint>> entrypointContainers) {
        Map<String, PluginContainer> pluginMap = new HashMap<>();
        entrypointContainers.forEach(entry -> addPlugin(pluginMap, entry));
        return ImmutableBiMap.copyOf(pluginMap);
    }

    private void generatePlugins() {
        pluginMapInitHelper.initialize(collectPluginMap(
                fabricLoader.getEntrypointContainers("plugin", PluginEntrypoint.class)
        ));
    }

    private void addPlugin(Map<String, PluginContainer> pluginMap, EntrypointContainer<PluginEntrypoint> entrypointContainer) {
        final var metadata = entrypointContainer.getProvider().getMetadata();

        if (pluginMap.containsKey(metadata.getId())) {
            throw new IllegalStateException("Duplicate initialization points. Plugin: " + metadata.getId());
        }

        PluginContainer container = new PluginContainer(
                metadata.getId(),
                metadata.getName(),
                metadata.getDescription(),
                entrypointContainer.getEntrypoint(),
                LoggerFactory.getLogger(metadata.getName())
        );

        pluginMap.put(metadata.getId(), container);
    }

    private void loadPlugins() {
        final var registryMap = new ConcurrentHashMap<SubscriberRegistry, EventRegistry>();

        pluginMapInitHelper.get().values().parallelStream()
                .forEach(plugin -> loadPlugin(plugin, registryMap));

        eventManagerInitHelper.initialize(() -> EventManager.initialize(registryMap));
    }

    private void loadPlugin(PluginContainer plugin, Map<SubscriberRegistry, EventRegistry> registryMap) {
        PluginEntrypoint entrypoint = plugin.entrypoint();
        entrypoint.plugin.initialize(plugin);
        SubscriberRegistry subscriberRegistry = callPluginLoad(plugin, entrypoint);
        EventRegistry eventRegistry = callPluginEventRegistry(plugin, entrypoint);
        registryMap.put(subscriberRegistry, eventRegistry);
    }

    private SubscriberRegistry callPluginLoad(PluginContainer plugin, PluginEntrypoint entrypoint) {
        SubscriberRegistry subscriberRegistry = new SubscriberRegistry(plugin);
        entrypoint.onLoad(subscriberRegistry);
        return subscriberRegistry;
    }

    private EventRegistry callPluginEventRegistry(PluginContainer plugin, PluginEntrypoint entrypoint) {
        EventRegistry eventRegistry = new EventRegistry(plugin);
        entrypoint.registerEvent(eventRegistry);
        return eventRegistry;
    }

    public BiMap<String, PluginContainer> getPlugins() {
        return pluginMapInitHelper.get();
    }

    public EventManager getEventManager() {
        return eventManagerInitHelper.get();
    }
}
