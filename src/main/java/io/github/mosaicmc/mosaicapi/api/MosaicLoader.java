package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.event.EventManager;
import io.github.mosaicmc.mosaicapi.api.mc.Server;

public interface MosaicLoader {

    /**
     * Retrieves the server instance.
     *
     * @return The server instance.
     */
    Server getServer();

    /**
     * Retrieves the plugin manager responsible for managing plugins.
     *
     * @return The {@link PluginManager} instance.
     */
    PluginManager getPluginManager();

    /**
     * Retrieves the event manager responsible for managing events.
     *
     * @return The {@link EventManager} instance.
     */
    EventManager getEventManager();
}

