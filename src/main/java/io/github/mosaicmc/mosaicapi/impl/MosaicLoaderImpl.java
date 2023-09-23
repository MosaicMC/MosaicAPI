package io.github.mosaicmc.mosaicapi.impl;

import io.github.mosaicmc.mosaicapi.api.MosaicLoader;
import io.github.mosaicmc.mosaicapi.api.PluginManager;
import io.github.mosaicmc.mosaicapi.api.mc.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MosaicLoaderImpl implements MosaicLoader {
    private final Server server;
    private final Logger logger;
    private final PluginManager pluginManager;

    public MosaicLoaderImpl(Server server) {
        this.server = server;
        this.logger = LoggerFactory.getLogger("MosaicAPI");
        this.pluginManager = new PluginManagerImpl();
        onServerLoad();
    }

    private void onServerLoad() {
        logger.info("Loading Plugins...");
        pluginManager.loadPlugins(server);
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }
}
