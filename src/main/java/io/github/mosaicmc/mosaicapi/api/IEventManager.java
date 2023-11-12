package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.internal.Loader;
import org.jetbrains.annotations.ApiStatus;

/**
 * API interface for the event manager.
 */
@ApiStatus.NonExtendable
public interface IEventManager {
    /**
     * Get the instance of the event manager.
     *
     * @return The event manager instance.
     */
    static IEventManager getInstance() {
        return Loader.getInstance().eventManager;
    }

    /**
     * Calls the specified event.
     *
     * @param <T> the type of the event
     * @param event the event to be called
     */
    <T extends Event<T>> void callEvent(T event);
}
