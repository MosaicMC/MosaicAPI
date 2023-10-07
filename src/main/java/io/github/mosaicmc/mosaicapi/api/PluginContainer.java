package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.mc.Server;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;

public interface PluginContainer {

    /**
     * Retrieves the initializers for this plugin.
     *
     * @return An iterable collection of {@link PluginInitializer} instances.
     */
    Iterable<PluginInitializer> getInitializers();

    /**
     * Retrieves the server instance associated with this plugin.
     *
     * @return The server instance.
     */
    Server getServer();

    /**
     * Retrieves the path to the plugin's configuration directory.
     *
     * @return The configuration directory path.
     */
    Path getConfigPath();

    /**
     * Retrieves the name of the plugin.
     *
     * @return The plugin name.
     */
    String getName();

    /**
     * Retrieves the loader responsible for loading this plugin.
     *
     * @return The {@link MosaicLoader} instance.
     */
    MosaicLoader getLoader();

    /**
     * Retrieves the logger for this plugin.
     *
     * @return The {@link Logger} instance.
     */
    Logger getLogger();

    /**
     * Retrieves the configuration file for this plugin.
     *
     * @return The configuration file as a {@link File}.
     */
    default File getConfigFile() {
        return getConfigPath().toFile();
    }
}

