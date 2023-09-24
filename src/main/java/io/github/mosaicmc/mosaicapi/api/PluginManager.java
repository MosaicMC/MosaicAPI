package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.mc.Server;

public interface PluginManager {
    void craftPlugins(Server server);

    Iterable<PluginContainer> getPlugins();
}
