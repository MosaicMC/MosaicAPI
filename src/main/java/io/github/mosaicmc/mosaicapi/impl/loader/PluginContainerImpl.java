package io.github.mosaicmc.mosaicapi.impl.loader;

import io.github.mosaicmc.mosaicapi.api.loader.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.loader.PluginInitializer;
import io.github.mosaicmc.mosaicapi.api.mc.MosaicServer;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PluginContainerImpl implements PluginContainer {
    private final MosaicServer server;
    private final Path configDir;
    private final String id;
    private final Logger logger;
    private final List<PluginInitializer> initializers;

    public PluginContainerImpl(MosaicServer server, String id, List<PluginInitializer> initializers) {
        this.server = server;
        this.configDir = FabricLoader.getInstance().getConfigDir().resolve(id);
        loadConfigDirectory();
        this.id = id;
        this.logger = LoggerFactory.getLogger(id);
        this.initializers = initializers;
    }

    @Override
    public MosaicServer getServer() {
        return this.server;
    }

    @Override
    public Path getConfigDir() {
        return this.configDir;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public List<PluginInitializer> getInitializers() {
        return this.initializers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PluginContainer that)) return false;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private void loadConfigDirectory() {
        final var configDirFile = this.configDir.toFile();
        if (!configDirFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            configDirFile.mkdirs();
        }
    }
}
