package io.github.mosaicmc.mosaicapi.impl.event;

import io.github.mosaicmc.mosaicapi.api.event.*;
import io.github.mosaicmc.mosaicapi.util.BiIterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventManagerImpl implements EventManager {
    private final Map<Class<Event<?>>, List<Subscriber<?>>> events;

    public EventManagerImpl() {
        this.events = new ConcurrentHashMap<>();
    }

    @Override
    public BiIterable<Class<Event<?>>, List<Subscriber<?>>> getEvents() {
        return BiIterable.of(events);
    }

    @Override
    public void registerEvents(final EventRegistry eventRegistry) {
        eventRegistry.forEach(eventClass -> {
            if (isRegistered(eventClass)) {
                throw new IllegalStateException("Event " + eventClass + " is already registered");
            }
            events.put(eventClass, new ArrayList<>());
        });
    }

    @Override
    public boolean isRegistered(final Class<?> eventClass) {
        return events.containsKey(eventClass);
    }

    @Override
    public <T extends Event<T>> Iterable<Subscriber<T>> getEventSubscribers(final Class<T> eventClass) {
        if (!isRegistered(eventClass)) {
            throw new IllegalStateException("Event " + eventClass + " is not registered");
        }
        //noinspection unchecked
        return (Iterable<Subscriber<T>>) (Object) events.get(eventClass);
    }

    @Override
    public void registerSubscribers(final SubscriberRegistry registry) {
        registry.forEach(subscriber -> {
            if (!isRegistered(subscriber.getEventClass())) {
                throw new IllegalStateException("Event " + subscriber.getEventClass() + " is not registered");
            }
            events.get(subscriber.getEventClass()).add(subscriber);
        });
    }
}
