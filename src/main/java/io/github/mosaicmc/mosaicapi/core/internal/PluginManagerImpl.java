package io.github.mosaicmc.mosaicapi.core.internal;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import io.github.mosaicmc.mosaicapi.core.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.core.api.PluginEntrypoint;
import io.github.mosaicmc.mosaicapi.core.api.PluginManager;
import lombok.Data;
import lombok.Getter;
import lombok.val;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Internal class, used for plugin management.
 */
final class PluginManagerImpl implements PluginManager {
    private final FabricLoader fabricLoader;
    @Getter
    private final ImmutableBiMap<String, PluginContainer> plugins;

    PluginManagerImpl() {
        this.fabricLoader = FabricLoader.getInstance();
        this.plugins = generatePlugins();
    }

    private ImmutableBiMap<String, PluginContainer> generatePlugins() {
        val entrypointContainers = fabricLoader.getEntrypointContainers("plugin", PluginEntrypoint.class);
        if (entrypointContainers.isEmpty()) {
            return ImmutableBiMap.of();
        }
        return entrypointContainers
                .stream()
                .collect(Collectors.groupingBy(
                        HashedMetadata::from,
                        Collectors.mapping(EntrypointContainer::getEntrypoint, ImmutableList.toImmutableList())
                ))
                .entrySet()
                .stream()
                .collect(ImmutableBiMap.toImmutableBiMap(
                        entry -> entry.getKey().id,
                        entry -> createPluginContainer(entry.getKey(), entry.getValue())
                ));
    }

    private PluginContainer createPluginContainer(HashedMetadata metadata, List<PluginEntrypoint> entrypoints) {
        val logger = LoggerFactory.getLogger(metadata.name);

        PluginContainerImpl pluginContainer = new PluginContainerImpl(metadata.id, metadata.name, metadata.description, entrypoints, logger);
        pluginContainer.initialize();

        return pluginContainer;
    }

    @Data
    private static class HashedMetadata {
        private final String id;
        private final String name;
        private final String description;

        static HashedMetadata from(EntrypointContainer<PluginEntrypoint> entrypoint) {
            val metadata = entrypoint.getProvider().getMetadata();
            return new HashedMetadata(metadata.getId(), metadata.getName(), metadata.getDescription());
        }
    }
}
