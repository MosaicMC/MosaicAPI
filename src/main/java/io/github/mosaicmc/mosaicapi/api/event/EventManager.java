package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.util.BiIterable;

import java.util.List;

public interface EventManager extends SubscriberManager {

    /**
     * Retrieves a collection of events and their associated subscribers.
     *
     * @return A {@link BiIterable} containing event classes and their subscriber lists.
     */
    BiIterable<Class<Event<?>>, List<Subscriber<?>>> getEvents();

    /**
     * Registers events with the event manager using an {@link EventRegistry}.
     *
     * @param eventRegistry The registry containing event types and their subscribers.
     */
    void registerEvents(EventRegistry eventRegistry);

    /**
     * Checks if a specific event class is registered with the event manager.
     *
     * @param eventClass The class of the event to check for registration.
     * @return {@code true} if the event is registered, {@code false} otherwise.
     */
    boolean isRegistered(Class<?> eventClass);
}
