package io.github.mosaicmc.mosaicapi;

import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;

import java.nio.file.Path;

public interface PluginContainer extends Comparable<PluginContainer> {
    MinecraftServer getServer();

    Path getConfigDir();

    String getName();

    Logger getLogger();

    @Override
    default int compareTo(PluginContainer other) {
        return getName().compareTo(other.getName());
    }
}
