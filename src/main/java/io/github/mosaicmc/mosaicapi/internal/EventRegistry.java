package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.IEventRegistry;
import io.github.mosaicmc.mosaicapi.api.ISubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Getter;
import lombok.val;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * Internal class, used for event registration.
 */
public final class EventRegistry implements IEventRegistry {
    @Getter
    private final Map<Type<?>, EventContainer<?>> events;
    private final PluginContainer plugin;

    EventRegistry(PluginContainer plugin) {
        this.plugin = plugin;
        this.events = new ConcurrentHashMap<>();
    }

    @Override
    public <T extends Event<T>> void register(Type<T> event) {
        events.put(event, new EventContainer<>(event, (e, subs) -> {
            for (val sub : subs) {
                sub.getConsumer().accept(e);
            }
        }, plugin));
    }

    @Override
    public <T extends Event<T>> void registerParallel(Type<T> event) {
        events.put(event, new EventContainer<>(event,
                (e, subs) -> subs.parallelStream().forEach(sub -> sub.getConsumer().accept(e)),
                plugin
        ));
    }

    @Override
    public <T extends Event<T>> void register(Type<T> event, BiConsumer<T, Collection<ISubscriberContainer<T>>> consumer) {
        val container = new EventContainer<>(event, consumer, plugin);
        events.put(event, container);
    }
}
