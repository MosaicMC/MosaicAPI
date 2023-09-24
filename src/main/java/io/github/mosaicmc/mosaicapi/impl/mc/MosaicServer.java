package io.github.mosaicmc.mosaicapi.impl.mc;

import io.github.mosaicmc.mosaicapi.api.mc.Server;
import net.minecraft.server.MinecraftServer;

public final class MosaicServer implements Server {
    private final MinecraftServer server;

    public MosaicServer(MinecraftServer server) {
        this.server = server;
    }

    @Override
    public int getPlayerAmount() {
        return server.getCurrentPlayerCount();
    }
}
