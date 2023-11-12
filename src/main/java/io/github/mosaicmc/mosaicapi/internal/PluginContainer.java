package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.IPluginContainer;
import io.github.mosaicmc.mosaicapi.api.PluginEntrypoint;
import lombok.Getter;
import org.slf4j.Logger;

@SuppressWarnings("LombokGetterMayBeUsed")
record PluginContainer(
        @Getter String id,
        @Getter String name,
        @Getter String description,
        @Getter PluginEntrypoint entrypoint,
        @Getter Logger logger
) implements IPluginContainer {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PluginContainer that = (PluginContainer) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
