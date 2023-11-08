package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.IEventManager;
import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ApiStatus.Internal
public final class EventManager implements IEventManager {
    @SuppressWarnings("rawtypes")
    private final Map<Type<?>, EventRegContainer> events = new IdentityHashMap<>();

    private EventManager(Set<EventRegistry> eventRegs, Set<SubscriberRegistry> subscriberRegs) {
        registerEvents(eventRegs);
        registerSubscribers(subscriberRegs);
    }

    static EventManager initialize(Map<SubscriberRegistry, EventRegistry> registryMap) {
        final var values = Set.copyOf(registryMap.values());
        final var keys = registryMap.keySet();
        return new EventManager(values, keys);
    }

    private void registerEvents(Set<EventRegistry> eventRegs) {
        for (final var eventReg : eventRegs) {
            final var rawEvents = eventReg.getAll();
            rawEvents.forEach((type, event) -> {
                this.events.put(type, new EventRegContainer<>(event, ConcurrentHashMap.newKeySet()));
            });
        }
    }

    private void registerSubscribers(Set<SubscriberRegistry> subscriberRegs) {
        for (final var subscriberReg : subscriberRegs) {
            final var rawSubs = subscriberReg.getAll();
            rawSubs.forEach((eventType, sub) -> {
                if (!events.containsKey(eventType)) {
                    throw new IllegalStateException("Not registered event: " + eventType);
                }
                final var subs = events.get(eventType).subscribers();
                subs.add(sub);
            });
        }
    }

    @Override
    public <T extends Event<T>> void callEvent(T event) {
        if (!events.containsKey(event.getType())) {
            throw new IllegalStateException("Not registered event: " + event.getType());
        }

        final var subs = events.get(event.getType()).subscribers();

        events.get(event.getType()).event().callback().accept(event, subs);

        Loader.logger.info("Called event: {}", event.getType());
    }

    private record EventRegContainer<T extends Event<T>>(
            EventContainer<T> event,
            Set<SubscriberContainer<T>> subscribers) {
    }
}
