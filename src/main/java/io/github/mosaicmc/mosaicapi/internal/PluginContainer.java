package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.IPluginContainer;
import io.github.mosaicmc.mosaicapi.api.PluginEntrypoint;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;

import java.util.Objects;

@ApiStatus.Internal
public record PluginContainer(String id, String name, String description, PluginEntrypoint entrypoint, Logger logger)
        implements IPluginContainer {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PluginContainer plugin)) return false;
        return Objects.equals(this.id, plugin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
