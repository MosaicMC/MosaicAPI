package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.PluginContainer;

public interface Subscriber<T extends Event<T>> {

    /**
     * Retrieves the plugin container associated with this subscriber.
     *
     * @return The {@link PluginContainer} containing the plugin's events.
     */
    PluginContainer getPluginContainer();

    /**
     * Retrieves the class of events that this subscriber can handle.
     *
     * @return The event class this subscriber is designed for.
     */
    Class<T> getEventClass();

    /**
     * Handles the given event by invoking the subscriber's logic.
     *
     * @param event The event to be handled.
     */
    void call(T event);
}
