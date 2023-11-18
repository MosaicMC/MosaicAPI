package io.github.mosaicmc.mosaicapi.core.internal;

import io.github.mosaicmc.mosaicapi.core.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.core.api.PluginEntrypoint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;
import org.slf4j.Logger;

import java.util.List;

/**
 * Internal class, used for plugin registration.
 */
@Data
final class PluginContainerImpl implements PluginContainer {
    private final String id;
    @EqualsAndHashCode.Exclude
    private final String name;
    @EqualsAndHashCode.Exclude
    private final String description;
    @EqualsAndHashCode.Exclude
    private final List<PluginEntrypoint> entrypoints;
    @EqualsAndHashCode.Exclude
    private final Logger logger;

    void initialize() {
        entrypoints.forEach(entrypoint -> {
            try {
                val pluginField = entrypoint.getClass().getSuperclass().getDeclaredField("plugin");
                pluginField.trySetAccessible();
                pluginField.set(entrypoint, this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
