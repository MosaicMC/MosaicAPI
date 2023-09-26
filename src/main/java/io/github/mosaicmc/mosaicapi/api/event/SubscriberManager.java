package io.github.mosaicmc.mosaicapi.api.event;

public interface SubscriberManager {

    /**
     * Retrieves subscribers for a specific event class.
     *
     * @param eventClass The class of the event to get subscribers for.
     * @param <T>        The type of event for which subscribers are retrieved.
     * @return An iterable collection of subscribers for the specified event class.
     */
    <T extends Event<T>> Iterable<Subscriber<T>> getEventSubscribers(Class<T> eventClass);

    /**
     * Registers subscribers with the subscriber manager using a {@link SubscriberRegistry}.
     *
     * @param registry The registry containing subscriber information.
     */
    void registerSubscribers(SubscriberRegistry registry);
}
