package io.github.mosaicmc.mosaicapi.impl;

import io.github.mosaicmc.mosaicapi.api.MosaicLoader;
import io.github.mosaicmc.mosaicapi.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.PluginInitializer;
import io.github.mosaicmc.mosaicapi.api.mc.Server;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public final class PluginContainerImpl implements PluginContainer {
    private final List<PluginInitializer> initializers;
    private final Server server;
    private final Path configPath;
    private final String name;
    private final MosaicLoader loader;
    private final Logger logger;

    public PluginContainerImpl(List<PluginInitializer> initializers, Server server, String name, MosaicLoader loader) {
        this.initializers = initializers;
        this.server = server;
        this.configPath = FabricLoader.getInstance().getConfigDir().resolve(name);
        this.name = name;
        this.loader = loader;
        this.logger = LoggerFactory.getLogger(name);
    }

    @Override
    public Iterable<PluginInitializer> getInitializers() {
        return initializers;
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public Path getConfigPath() {
        return configPath;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MosaicLoader getLoader() {
        return loader;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final PluginContainerImpl that)) return false;
        return Objects.equals(initializers, that.initializers) && Objects.equals(server, that.server) && Objects.equals(configPath, that.configPath) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initializers, server, configPath, name);
    }
}
