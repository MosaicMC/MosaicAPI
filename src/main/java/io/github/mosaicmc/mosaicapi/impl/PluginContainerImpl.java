package io.github.mosaicmc.mosaicapi.impl;

import io.github.mosaicmc.mosaicapi.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.PluginInitializer;
import io.github.mosaicmc.mosaicapi.api.mc.Server;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public final class PluginContainerImpl implements PluginContainer {
    private final List<PluginInitializer> initializers;
    private final Server server;
    private final Path configPath;
    private final String name;

    public PluginContainerImpl(List<PluginInitializer> initializers, Server server, String name) {
        this.initializers = initializers;
        this.server = server;
        this.configPath = FabricLoader.getInstance().getConfigDir().resolve(name);
        this.name = name;
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
