package io.github.mosaicmc.mosaicapi;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public final class PluginContainerImpl implements PluginContainer {
    private final MinecraftServer server;
    private final Path configDir;
    private final String name;
    private final Logger logger;

    public PluginContainerImpl(MinecraftServer server, String name) {
        this.server = server;
        this.configDir = FabricLoader.getInstance().getConfigDir().resolve(name);
        this.name = name;
        this.logger = LoggerFactory.getLogger(name);
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
