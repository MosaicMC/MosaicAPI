package io.github.mosaicmc.mosaicapi.loader;

import io.github.mosaicmc.mosaicapi.mc.MosaicServer;
import java.nio.file.Path;
import java.util.Objects;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract sealed class PluginContainer implements Comparable<PluginContainer> permits PluginContainerImpl {
    protected final MosaicServer server;
    protected final Path configDir;
    protected final String name;
    protected final Logger logger;

    public static PluginContainer of(MosaicServer server, String name) {
        return new PluginContainerImpl(server, name);
    }

    protected PluginContainer(MosaicServer server, String name) {
        this.server = server;
        this.configDir = FabricLoader.getInstance().getConfigDir().resolve(name);
        {
            final var configDirFile = this.configDir.toFile();
            if (!configDirFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                configDirFile.mkdirs();
            }
        }
        this.name = name;
        this.logger = LoggerFactory.getLogger(name);
    }

    public abstract MosaicServer getServer();

    public abstract Path getConfigDir();

    public abstract String getName();

    public abstract Logger getLogger();

    @Override
    public int compareTo(PluginContainer other) {
        return getName().compareTo(other.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PluginContainer that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
