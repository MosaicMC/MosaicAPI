package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.mc.Server;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;

public interface PluginContainer {
    Iterable<PluginInitializer> getInitializers();

    Server getServer();

    Path getConfigPath();

    String getName();

    MosaicLoader getLoader();

    Logger getLogger();

    default File getConfigFile() {
        return getConfigPath().toFile();
    }
}
