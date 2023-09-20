package io.github.mosaicmc.mosaicapi.impl.mc;

import io.github.mosaicmc.mosaicapi.api.loader.Loader;
import io.github.mosaicmc.mosaicapi.api.mc.MosaicServer;
import io.github.mosaicmc.mosaicapi.impl.loader.LoaderImpl;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class MosaicServerImpl implements MosaicServer {
    private final MinecraftServer server;
    private final Loader loader;

    public MosaicServerImpl(MinecraftServer server) {
        this.server = server;
        this.loader = new LoaderImpl(this);
    }

    @Override
    public int serverSize() {
        return server.getPlayerCount();
    }

    @Override
    public Loader getLoader() {
        return loader;
    }
}
