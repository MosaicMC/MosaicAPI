package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.ImmutableBiMap;
import net.minecraft.server.MinecraftServer;

public final class LoaderImpl extends Loader {
    public LoaderImpl(MinecraftServer server) {
        super(server);
    }

    @Override
    protected ImmutableBiMap<String, PluginContainer> loadPlugins() {
        final var pluginsBuilder = ImmutableBiMap.<String, PluginContainer>builder();
        return pluginsBuilder.build();
    }
}
