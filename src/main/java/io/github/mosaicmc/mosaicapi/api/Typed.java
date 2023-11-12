package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.utils.Type;

/**
 * For typed classes.
 */
public interface Typed<T> {
    /**
     * Retrieves the type of the object.
     *
     * @return the type of the object
     */
    Type<T> getType();
}
