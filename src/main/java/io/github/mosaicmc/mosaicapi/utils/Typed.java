package io.github.mosaicmc.mosaicapi.utils;

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
