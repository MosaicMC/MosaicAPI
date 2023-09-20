package io.github.mosaicmc.mosaicapi.impl.loader;

import io.github.mosaicmc.mosaicapi.api.loader.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.loader.PluginContainerBuilder;
import io.github.mosaicmc.mosaicapi.api.loader.PluginInitializer;
import io.github.mosaicmc.mosaicapi.api.mc.MosaicServer;
import java.util.ArrayList;
import java.util.List;

public final class PluginContainerBuilderImpl implements PluginContainerBuilder {
    private final String name;
    private final MosaicServer server;
    private final List<PluginInitializer> initializers = new ArrayList<>();

    public PluginContainerBuilderImpl(String name, MosaicServer server) {
        this.name = name;
        this.server = server;
    }

    @Override
    public void add(PluginInitializer init) {
        initializers.add(init);
    }

    @Override
    public PluginContainer build() {
        return new PluginContainerImpl(server, name, List.copyOf(initializers));
    }
}
