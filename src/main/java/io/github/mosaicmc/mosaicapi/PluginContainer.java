package io.github.mosaicmc.mosaicapi;

import java.nio.file.Path;
import java.util.Objects;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract sealed class PluginContainer implements Comparable<PluginContainer> permits PluginContainerImpl {
    protected final MinecraftServer server;
    protected final Path configDir;
    protected final String name;
    protected final Logger logger;

    protected PluginContainer(MinecraftServer server, String name) {
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

    public abstract MinecraftServer getServer();

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
