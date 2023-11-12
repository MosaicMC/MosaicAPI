package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.IPluginContainer;
import io.github.mosaicmc.mosaicapi.api.PluginEntrypoint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;

/**
 * Internal class, used for plugin registration.
 */
@Data
class PluginContainer implements IPluginContainer {
    private final String id;
    @EqualsAndHashCode.Exclude private final String name;
    @EqualsAndHashCode.Exclude private final String description;
    @EqualsAndHashCode.Exclude private final PluginEntrypoint entrypoint;
    @EqualsAndHashCode.Exclude private final Logger logger;
}
