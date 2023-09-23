package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.mc.Server;

public interface MosaicLoader {
    Server getServer();

    PluginManager getPluginManager();
}
