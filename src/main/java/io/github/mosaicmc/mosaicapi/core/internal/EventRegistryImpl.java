package io.github.mosaicmc.mosaicapi.core.internal;

import io.github.mosaicmc.mosaicapi.core.api.Event;
import io.github.mosaicmc.mosaicapi.core.api.EventRegistry;
import io.github.mosaicmc.mosaicapi.core.api.SubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Getter;
import lombok.val;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * Internal class, used for event registration.
 */
final class EventRegistryImpl implements EventRegistry {
    @Getter
    private final Map<Type<?>, EventContainerImpl<?>> events;
    private final PluginContainerImpl plugin;

    EventRegistryImpl(PluginContainerImpl plugin) {
        this.plugin = plugin;
        this.events = new ConcurrentHashMap<>();
    }

    @Override
    public <T extends Event<T>> void register(Type<T> event) {
        register(event, (e, subs) -> subs.forEach(sub -> sub.getConsumer().accept(e)));
    }

    @Override
    public <T extends Event<T>> void registerParallel(Type<T> event) {
        register(event, (e, subs) -> subs.parallelStream().forEach(sub -> sub.getConsumer().accept(e)));
    }

    @Override
    public <T extends Event<T>> void register(Type<T> event, BiConsumer<T, Set<SubscriberContainer<T>>> consumer) {
        val container = new EventContainerImpl<>(event, consumer, plugin);
        events.put(event, container);
    }
}
