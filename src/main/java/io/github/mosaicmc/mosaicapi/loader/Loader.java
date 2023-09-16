package io.github.mosaicmc.mosaicapi.loader;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.mosaicmc.mosaicapi.mc.MosaicServer;

public abstract sealed class Loader permits LoaderImpl {
    public final MosaicServer server;
    public final ImmutableMap<PluginContainer, ImmutableList<PluginInitializer>> plugins;

    public static Loader of(MosaicServer server) {
        return new LoaderImpl(server);
    }

    protected Loader(MosaicServer server) {
        this.server = server;
        this.plugins = createPluginsMap();
    }

    protected abstract ImmutableMap<PluginContainer, ImmutableList<PluginInitializer>> createPluginsMap();

    protected abstract PluginContainer mapToPlugin(String name);
}
