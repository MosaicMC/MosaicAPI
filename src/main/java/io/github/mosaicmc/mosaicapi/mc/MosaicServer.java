package io.github.mosaicmc.mosaicapi.mc;

import net.minecraft.server.MinecraftServer;

public abstract sealed class MosaicServer permits MosaicServerImpl {
    protected final MinecraftServer server;

    public static MosaicServer of(MinecraftServer server) {
        return new MosaicServerImpl(server);
    }

    protected MosaicServer(MinecraftServer server) {
        this.server = server;
    }

    public abstract int serverSize();
}
