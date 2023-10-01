package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.event.EventManager;
import io.github.mosaicmc.mosaicapi.api.mc.Server;
import io.github.mosaicmc.mosaicapi.util.Option;

public interface MosaicLoader {
    MosaicLoader INSTANCE = null;
    public static MosaicLoader getInstance(final Server server) {
        final Option<String> test = Option.None();
        return null;
    }



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

