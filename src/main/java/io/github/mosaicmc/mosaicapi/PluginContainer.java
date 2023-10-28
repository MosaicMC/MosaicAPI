package io.github.mosaicmc.mosaicapi;

import com.google.common.base.Objects;
import org.slf4j.Logger;

public record PluginContainer(String name, PluginEntrypoint entrypoint, Logger logger) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PluginContainer(String nameVar, PluginEntrypoint $, Logger $$))) return false;
        return Objects.equal(this.name, nameVar);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
