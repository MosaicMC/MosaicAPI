package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.mc.Server;

import java.io.File;
import java.nio.file.Path;

public interface PluginContainer {
    Iterable<PluginInitializer> getInitializers();

    Server getServer();

    Path getConfigPath();

    String getName();

    default File getConfigFile() {
        return getConfigPath().toFile();
    }
}
