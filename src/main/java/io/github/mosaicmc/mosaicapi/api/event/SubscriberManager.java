package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.Event;

public interface SubscriberManager {
    Iterable<Subscriber<?>> getSubscribers();
    <T extends Event<T>> Iterable<Subscriber<T>> getEventSubscribers(Class<T> eventClass);
    void registerSubscribers(SubscriberRegistry registry);
}
