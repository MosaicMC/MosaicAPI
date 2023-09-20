package io.github.mosaicmc.mosaicapi.api.loader;

import io.github.mosaicmc.mosaicapi.api.mc.MosaicServer;
import io.github.mosaicmc.mosaicapi.impl.loader.PluginContainerBuilderImpl;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;

public interface PluginContainer extends Comparable<PluginContainer> {
    static PluginContainerBuilder builder(String name, MosaicServer server) {
        return new PluginContainerBuilderImpl(name, server);
    }

    MosaicServer getServer();

    Path getConfigDir();

    String getId();

    Logger getLogger();

    List<PluginInitializer> getInitializers();

    @Override
    default int compareTo(PluginContainer other) {
        return getId().compareTo(other.getId());
    }
}
