package io.github.mosaicmc.mosaicapi.core.api;

/**
 * Base class for all events.
 */
public abstract class Event<T extends Event<T>> implements Typed<T> {
}
