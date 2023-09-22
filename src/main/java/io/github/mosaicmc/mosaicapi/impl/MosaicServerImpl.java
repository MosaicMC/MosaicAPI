package io.github.mosaicmc.mosaicapi.impl;

import io.github.mosaicmc.mosaicapi.api.MosaicServer;
import net.minecraft.server.MinecraftServer;

public class MosaicServerImpl implements MosaicServer {
    private final MinecraftServer server;

    public MosaicServerImpl(MinecraftServer server) {
        this.server = server;
    }

    @Override
    public int getPlayerAmount() {
        return server.getCurrentPlayerCount();
    }
}
