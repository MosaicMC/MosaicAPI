package io.github.mosaicmc.mosaicapi.internal;

import com.google.common.collect.ImmutableList;
import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@ApiStatus.Internal
public final class EventRegistry {
    private final Map<Type<?>, EventContainer<?>> events;
    private final PluginContainer plugin;

    public EventRegistry(PluginContainer plugin) {
        this.plugin = plugin;
        this.events = new IdentityHashMap<>();
    }

    public <T extends Event<T>> void register(Type<T> event) {
        events.put(event, new EventContainer<>(event, (e, subs) -> {
            for (var sub : subs) {
                sub.consumer().accept(e);
            }
        }, plugin));
    }

    public <T extends Event<T>> void registerParallel(Type<T> event) {
        events.put(event, new EventContainer<>(event,
                (e, subs) -> subs.parallelStream().forEach(sub -> sub.consumer().accept(e)),
                plugin
        ));
    }

    public <T extends Event<T>> void register(Type<T> event, BiConsumer<T, ImmutableList<SubscriberContainer<T>>> consumer) {
        events.put(event, new EventContainer<>(event, consumer, plugin));
    }

    public Map<Type<?>, EventContainer<?>> build() {
        return Map.copyOf(events);
    }
}
