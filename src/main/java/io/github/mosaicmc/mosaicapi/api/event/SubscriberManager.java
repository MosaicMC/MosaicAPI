package io.github.mosaicmc.mosaicapi.api.event;

public interface SubscriberManager {
    <T extends Event<T>> Iterable<Subscriber<T>> getEventSubscribers(Class<T> eventClass);

    void registerSubscribers(SubscriberRegistry registry);
}
