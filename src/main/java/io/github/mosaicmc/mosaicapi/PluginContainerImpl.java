package io.github.mosaicmc.mosaicapi;

import java.nio.file.Path;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;

public final class PluginContainerImpl extends PluginContainer {
    public PluginContainerImpl(MinecraftServer server, String name) {
        super(server, name);
    }

    @Override
    public MinecraftServer getServer() {
        return this.server;
    }

    @Override
    public Path getConfigDir() {
        return this.configDir;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }
}
