package io.github.mosaicmc.mosaicapi.core.internal;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import io.github.mosaicmc.mosaicapi.core.api.*;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Internal class, used for event management.
 */
final class EventManagerImpl implements EventManager {
    private final BiMap<Type<?>, ContainerWrapper> handlerMap;

    EventManagerImpl(BiMap<SubscriberRegistryImpl, EventRegistryImpl> registryMap) {
        this.handlerMap = subscribersToEvents(registryMap);
    }



    private BiMap<Type<?>, ContainerWrapper> subscribersToEvents(BiMap<SubscriberRegistryImpl, EventRegistryImpl> registryMap) {
        val builder = new ConcurrentHashMap<Type<?>, ContainerWrapper>(registryMap.size());
        registerEvents(builder, registryMap.values());
        registerSubscribers(builder, registryMap.keySet());
        return ImmutableBiMap.copyOf(builder);
    }

    private void registerEvents(Map<Type<?>, ContainerWrapper> builder, Collection<EventRegistryImpl> eventRegs) {
        eventRegs.stream()
                .flatMap(registry -> registry.getEvents().entrySet().parallelStream())
                .forEach(entry -> builder.put(entry.getKey(), new ContainerWrapper(entry.getValue(), ConcurrentHashMap.newKeySet())));
    }

    private void registerSubscribers(Map<Type<?>, ContainerWrapper> builder, Collection<SubscriberRegistryImpl> subscriberRegs) {
        subscriberRegs.stream()
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
        if (container == null) throw new IllegalStateException("Not registered event: " + event.getType().getName());
        if (container.subscribers.isEmpty()) return;
        val subs = (Collection<SubscriberContainer<T>>) (Object) container.subscribers;
        val eventContainer = (EventContainerImpl<T>) container.eventContainer;
        eventContainer.getConsumer().accept(event, subs);
        LoaderImpl.logger.debug("Called event: {}", event.getType().getName());
    }

    @Data
    static class ContainerWrapper {
        final EventContainerImpl<?> eventContainer;
        @EqualsAndHashCode.Exclude
        final Collection<SubscriberContainer<?>> subscribers;
    }
}
