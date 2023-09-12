package io.github.mosaicmc.mosaicapi;

import java.nio.file.Path;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;

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
