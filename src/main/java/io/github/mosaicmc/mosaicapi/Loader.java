package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.ImmutableBiMap;
import net.minecraft.server.MinecraftServer;

public abstract sealed class Loader permits LoaderImpl {
    public final MinecraftServer server;
    public final ImmutableBiMap<String, PluginContainer> plugins;

    public Loader(MinecraftServer server) {
        this.server = server;
        this.plugins = loadPlugins();
    }

    protected abstract ImmutableBiMap<String, PluginContainer> loadPlugins();
}
