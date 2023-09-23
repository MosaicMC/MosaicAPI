package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.mc.Server;
import io.github.mosaicmc.mosaicapi.util.BiIterable;

public interface PluginManager {
    void loadPlugins(Server server);

    BiIterable<String, PluginContainer> getPlugins();
}
