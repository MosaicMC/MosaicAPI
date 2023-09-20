package io.github.mosaicmc.mosaicapi.loader;

import io.github.mosaicmc.mosaicapi.mc.MosaicServer;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;

public final class PluginContainerImpl extends PluginContainer {
    PluginContainerImpl(MosaicServer server, String name, List<PluginInitializer> initializer, Loader loader) {
        super(server, name, initializer, loader);
    }

    @Override
    public Loader getLoader() {
        return loader;
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

    protected static final class BuilderImpl extends Builder {
        BuilderImpl(String name, MosaicServer server, Loader loader) {
            super(name, server, loader);
        }

        @Override
        public void add(PluginInitializer init) {
            initializers.add(init);
        }

        @Override
        public PluginContainer build() {
            return new PluginContainerImpl(server, name, List.copyOf(initializers), loader);
        }
    }
}
