package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.util.BiIterable;

public interface EventManager extends SubscriberManager {
    BiIterable<Class<Event<?>>, Subscriber<?>> getEvents();
    <T extends Event<T>> void registerEvents(EventRegistry eventRegistry);
    <T extends Event<T>> void isRegistered(Class<T> eventClass);
}
