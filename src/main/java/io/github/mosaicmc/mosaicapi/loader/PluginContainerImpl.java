package io.github.mosaicmc.mosaicapi.loader;

import io.github.mosaicmc.mosaicapi.mc.MosaicServer;
import org.slf4j.Logger;

import java.nio.file.Path;

public final class PluginContainerImpl extends PluginContainer {
    PluginContainerImpl(MosaicServer server, String name) {
        super(server, name);
    }

    @Override
    public MosaicServer getServer() {
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