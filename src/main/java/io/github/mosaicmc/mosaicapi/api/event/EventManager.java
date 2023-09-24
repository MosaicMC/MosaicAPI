package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.util.BiIterable;

import java.util.List;

public interface EventManager extends SubscriberManager {
    BiIterable<Class<Event<?>>, List<Subscriber<?>>> getEvents();

    void registerEvents(EventRegistry eventRegistry);

    boolean isRegistered(Class<?> eventClass);
}
