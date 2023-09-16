package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.MinecraftServer;

public abstract sealed class Loader permits LoaderImpl {
    public final MinecraftServer server;
    public final ImmutableMap<PluginContainer, ImmutableList<PluginInitializer>> plugins;

    protected Loader(MinecraftServer server) {
        this.server = server;
        this.plugins = createPluginsMap();
        System.out.println(this.plugins);
    }

    protected abstract ImmutableMap<PluginContainer, ImmutableList<PluginInitializer>> createPluginsMap();

    protected abstract PluginContainer mapToPlugin(String name);
}
