package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.mc.Server;

public interface PluginManager {

    /**
     * Crafts and initializes the plugins associated with the given server.
     *
     * @param server The server instance for which plugins should be crafted.
     */
    void craftPlugins(Server server);

    /**
     * Retrieves the plugins currently managed by this plugin manager.
     *
     * @return An iterable collection of {@link PluginContainer} instances.
     */
    Iterable<PluginContainer> getPlugins();
}

