package io.github.mosaicmc.mosaicapi.core.api;

import io.github.mosaicmc.mosaicapi.core.internal.LoaderImpl;
import org.jetbrains.annotations.ApiStatus;

/**
 * API interface for the event manager.
 */
@ApiStatus.NonExtendable
public interface EventManager {
    /**
     * Get the instance of the event manager.
     *
     * @return The event manager instance.
     */
    static EventManager getInstance() {
        return LoaderImpl.getInstance().getEventManager();
    }

    /**
     * Calls the specified event.
     *
     * @param <T>   the type of the event
     * @param event the event to be called
     */
    <T extends Event<T>> void callEvent(T event);
}
