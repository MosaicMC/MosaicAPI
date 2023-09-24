package io.github.mosaicmc.mosaicapi.impl.mc;

import io.github.mosaicmc.mosaicapi.api.MosaicLoader;
import io.github.mosaicmc.mosaicapi.api.mc.Server;
import io.github.mosaicmc.mosaicapi.util.ServerHelper;
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

    @Override
    public MosaicLoader getLoader() {
        return ((ServerHelper) server).mosaicAPI$getLoader();
    }
}
