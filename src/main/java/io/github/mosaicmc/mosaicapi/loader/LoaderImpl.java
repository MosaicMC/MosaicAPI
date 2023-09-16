package io.github.mosaicmc.mosaicapi.loader;

import static java.util.stream.Collectors.toList;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.mosaicmc.mosaicapi.mc.MosaicServer;
import java.util.stream.Collectors;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;

public final class LoaderImpl extends Loader {
    LoaderImpl(MosaicServer server) {
        super(server);
    }

    @Override
    protected ImmutableMap<PluginContainer, ImmutableList<PluginInitializer>> createPluginsMap() {
        final var pluginEntries = FabricLoader.getInstance().getEntrypointContainers("plugin", PluginInitializer.class);

        final var groupedPlugins = pluginEntries.stream()
                .collect(Collectors.groupingBy(
                        plugin -> plugin.getProvider().getMetadata().getId(),
                        Collectors.mapping(EntrypointContainer::getEntrypoint, toList())));

        final var immutableMapBuilder = ImmutableMap.<PluginContainer, ImmutableList<PluginInitializer>>builder();

        for (final var plugin : groupedPlugins.entrySet()) {
            final var name = plugin.getKey();
            final var inits = plugin.getValue();
            final var pluginContainer = mapToPlugin(name);
            final var immutableInits = ImmutableList.copyOf(inits);

            immutableMapBuilder.put(pluginContainer, immutableInits);
        }

        return immutableMapBuilder.build();
    }

    @Override
    protected PluginContainer mapToPlugin(String name) {
        return PluginContainer.of(server, name);
    }
}
