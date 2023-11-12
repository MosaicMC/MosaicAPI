package io.github.mosaicmc.mosaicapi.internal;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import io.github.mosaicmc.mosaicapi.api.IPluginContainer;
import io.github.mosaicmc.mosaicapi.api.IPluginManager;
import io.github.mosaicmc.mosaicapi.api.PluginEntrypoint;
import lombok.Getter;
import lombok.val;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PluginManager implements IPluginManager {
    private final FabricLoader fabricLoader;
    private final BiMap<String, PluginContainer> plugins;
    @Getter
    private final EventManager eventManager;

    PluginManager() {
        this.fabricLoader = FabricLoader.getInstance();
        this.plugins = generatePlugins();
        this.eventManager = loadPlugins();
    }

    private BiMap<String, PluginContainer> generatePlugins() {
        val entrypointContainers = fabricLoader.getEntrypointContainers("plugin", PluginEntrypoint.class);
        val pluginMap = new HashMap<String, PluginContainer>();
        entrypointContainers.forEach(entry -> addPlugin(pluginMap, entry));
        return ImmutableBiMap.copyOf(pluginMap);
    }

    private void addPlugin(Map<String, PluginContainer> pluginMap, EntrypointContainer<PluginEntrypoint> entrypointContainer) {
        val metadata = entrypointContainer.getProvider().getMetadata();

        if (pluginMap.containsKey(metadata.getId())) {
            throw new IllegalStateException("Duplicate initialization points. Plugin: " + metadata.getId());
        }

        val container = createPluginContainer(metadata, entrypointContainer.getEntrypoint());

        pluginMap.put(metadata.getId(), container);
    }

    private PluginContainer createPluginContainer(ModMetadata metadata, PluginEntrypoint entrypoint) {
        val id = metadata.getId();
        val name = metadata.getName();
        val description = metadata.getDescription();
        val logger = LoggerFactory.getLogger(name);

        return new PluginContainer(id, name, description, entrypoint, logger);
    }

    private EventManager loadPlugins() {
        val registryMap = new ConcurrentHashMap<SubscriberRegistry, EventRegistry>();

        plugins.values().parallelStream()
                .forEach(plugin -> loadPlugin(plugin, registryMap));

        return new EventManager(registryMap);
    }

    private void loadPlugin(PluginContainer plugin, Map<SubscriberRegistry, EventRegistry> registryMap) {
        val entrypoint = plugin.entrypoint();

        entrypoint.plugin.initialize(plugin);

        val subscriberRegistry = callPluginLoad(plugin, entrypoint);
        val eventRegistry = callPluginEventRegistry(plugin, entrypoint);

        registryMap.put(subscriberRegistry, eventRegistry);
    }

    private SubscriberRegistry callPluginLoad(PluginContainer plugin, PluginEntrypoint entrypoint) {
        val subscriberRegistry = new SubscriberRegistry(plugin);
        entrypoint.onLoad(subscriberRegistry);
        return subscriberRegistry;
    }

    private EventRegistry callPluginEventRegistry(PluginContainer plugin, PluginEntrypoint entrypoint) {
        val eventRegistry = new EventRegistry(plugin);
        entrypoint.registerEvent(eventRegistry);
        return eventRegistry;
    }

    @Override
    public BiMap<String, ? extends IPluginContainer> getPlugins() {
        return plugins;
    }
}
