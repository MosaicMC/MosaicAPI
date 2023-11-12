package io.github.mosaicmc.mosaicapi.api;

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;

/**
 * API interface for the plugin container.
 */
@ApiStatus.NonExtendable
public interface IPluginContainer {
    /**
     * Get the ID of the object.
     *
     * @return The ID as a string.
     */
    String getId();
    /**
     * Retrieve the logger instance.
     *
     * @return The logger instance.
     */
    Logger getLogger();
    /**
     * Returns the name.
     *
     * @return the name
     */
    String getName();
    /**
     * Returns the description of the object.
     *
     * @return the description of the object
     */
    String getDescription();}
