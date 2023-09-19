package io.github.mosaicmc.mosaicapi.loader;

import io.github.mosaicmc.mosaicapi.mc.MosaicServer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract sealed class PluginContainer implements Comparable<PluginContainer> permits PluginContainerImpl {
    protected final MosaicServer server;
    protected final Path configDir;
    protected final String id;
    protected final Logger logger;
    protected final List<PluginInitializer> initializers;
    protected final Loader loader;

    public static PluginContainer.Builder builder(String name, MosaicServer server, Loader loader) {
        return new PluginContainerImpl.BuilderImpl(name, server, loader);
    }

    public abstract static sealed class Builder permits PluginContainerImpl.BuilderImpl {
        protected final String name;
        protected final MosaicServer server;
        protected final List<PluginInitializer> initializers = new ArrayList<>();
        protected final Loader loader;

        protected Builder(String name, MosaicServer server, Loader loader) {
            this.name = name;
            this.server = server;
            this.loader = loader;
        }

        public abstract void add(PluginInitializer init);

        public abstract PluginContainer build();
    }

    protected PluginContainer(MosaicServer server, String id, List<PluginInitializer> initializers, Loader loader) {
        this.server = server;
        this.configDir = FabricLoader.getInstance().getConfigDir().resolve(id);
        {
            final var configDirFile = this.configDir.toFile();
            if (!configDirFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                configDirFile.mkdirs();
            }
        }
        this.id = id;
        this.logger = LoggerFactory.getLogger(id);
        this.initializers = initializers;
        this.loader = loader;
    }

    public abstract Loader getLoader();

    public abstract MosaicServer getServer();

    public abstract Path getConfigDir();

    public abstract String getId();

    public abstract Logger getLogger();

    @Override
    public int compareTo(PluginContainer other) {
        return getId().compareTo(other.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PluginContainer that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
