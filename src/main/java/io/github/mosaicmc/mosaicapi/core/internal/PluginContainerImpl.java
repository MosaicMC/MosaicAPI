package io.github.mosaicmc.mosaicapi.core.internal;

import io.github.mosaicmc.mosaicapi.core.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.core.api.PluginEntrypoint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;

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
    private final PluginEntrypoint entrypoint;
    @EqualsAndHashCode.Exclude
    private final Logger logger;
}
