package io.github.mosaicmc.mosaicapi.internal;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.IEventManager;
import io.github.mosaicmc.mosaicapi.api.ISubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.val;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class EventManager implements IEventManager {
    private final BiMap<Type<?>, ContainerWrapper> handlerMap;

    EventManager(Map<SubscriberRegistry, EventRegistry> registryMap) {
        this.handlerMap = combineEventsWithSubscribers(registryMap);
    }

    private BiMap<Type<?>, ContainerWrapper> combineEventsWithSubscribers(Map<SubscriberRegistry, EventRegistry> registryMap) {
        val builder = new ConcurrentHashMap<Type<?>, ContainerWrapper>(registryMap.size());

        registerEvents(builder, registryMap.values());
        registerSubscribers(builder, registryMap.keySet());

        return ImmutableBiMap.copyOf(builder);
    }

    private void registerEvents(Map<Type<?>, ContainerWrapper> builder, Collection<EventRegistry> eventRegs) {
        eventRegs.parallelStream()
                .flatMap(registry -> registry.getEvents().entrySet().parallelStream())
                .forEach(entry ->
                        builder.put(entry.getKey(), new ContainerWrapper(entry.getValue(), ConcurrentHashMap.newKeySet()))
                );
    }

    private void registerSubscribers(Map<Type<?>, ContainerWrapper> builder, Collection<SubscriberRegistry> subscriberRegs) {
        subscriberRegs.parallelStream()
                .flatMap(registry -> registry.getSubscribers().entrySet().parallelStream())
                .forEach(entry -> {
                    val type = entry.getKey();
                    val sub = entry.getValue();
                    val eventContainer = builder.get(type);

                    if (eventContainer == null) {
                        throw new IllegalStateException("Not registered event: " + type);
                    }

                    eventContainer.subscribers.add(sub);
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Event<T>> void callEvent(T event) {
        val container = handlerMap.get(event.getType());

        if (container == null) throw new IllegalStateException("Not registered event: " + event.getType().name());
        if (container.subscribers.isEmpty()) return;

        val subs = (Collection<ISubscriberContainer<T>>) (Object) container.subscribers;
        val eventContainer = (EventContainer<T>) container.eventContainer;
        eventContainer.consumer().accept(event, subs);

        Loader.logger.debug("Called event: {}", event.getType().name());
    }

    private record ContainerWrapper(
            EventContainer<?> eventContainer,
            Collection<ISubscriberContainer<?>> subscribers
    ) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ContainerWrapper that = (ContainerWrapper) o;

            return eventContainer.equals(that.eventContainer);
        }

        @Override
        public int hashCode() {
            return eventContainer.hashCode();
        }
    }
}
