package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.server.MinecraftServer;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public final class LoaderImpl extends Loader {
    public LoaderImpl(MinecraftServer server) {
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
            final var initializers = ImmutableList.copyOf(inits);

            immutableMapBuilder.put(pluginContainer, initializers);
        }

        return immutableMapBuilder.build();
    }

    @Override
    protected PluginContainer mapToPlugin(String name) {
        return new PluginContainerImpl(server, name);
    }
}
