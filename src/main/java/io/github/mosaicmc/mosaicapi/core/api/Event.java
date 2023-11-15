package io.github.mosaicmc.mosaicapi.core.api;

import io.github.mosaicmc.mosaicapi.utils.Typed;

/**
 * Base class for all events.
 */
public abstract class Event<T extends Event<T>> implements Typed<T> {
}
