package io.github.mosaicmc.mosaicapi.core.internal;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import io.github.mosaicmc.mosaicapi.core.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.core.api.PluginEntrypoint;
import io.github.mosaicmc.mosaicapi.core.api.PluginManager;
import lombok.Getter;
import lombok.val;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Internal class, used for plugin management.
 */
final class PluginManagerImpl implements PluginManager {
    private final FabricLoader fabricLoader;
    @Getter
    private final BiMap<String, PluginContainer> plugins;

    PluginManagerImpl() {
        this.fabricLoader = FabricLoader.getInstance();
        this.plugins = generatePlugins();
    }



    private BiMap<String, PluginContainer> generatePlugins() {
        val entrypointContainers = fabricLoader.getEntrypointContainers("plugin", PluginEntrypoint.class);
        val pluginMap = new ConcurrentHashMap<String, PluginContainerImpl>();
        entrypointContainers.forEach(entry -> addPlugin(pluginMap, entry));
        return ImmutableBiMap.copyOf(pluginMap);
    }

    private void addPlugin(Map<String, PluginContainerImpl> pluginMap, EntrypointContainer<PluginEntrypoint> entrypointContainer) {
        val metadata = entrypointContainer.getProvider().getMetadata();
        val entrypoint = entrypointContainer.getEntrypoint();

        if (pluginMap.containsKey(metadata.getId())) {
            throw new IllegalStateException("Duplicate initialization points. Plugin: " + metadata.getId());
        }

        val container = createPluginContainer(metadata, entrypoint);

        pluginMap.put(metadata.getId(), container);
        entrypoint.plugin.initialize(container);
    }

    private PluginContainerImpl createPluginContainer(ModMetadata metadata, PluginEntrypoint entrypoint) {
        val id = metadata.getId();
        val name = metadata.getName();
        val description = metadata.getDescription();
        val logger = LoggerFactory.getLogger(name);

        return new PluginContainerImpl(id, name, description, entrypoint, logger);
    }
}
