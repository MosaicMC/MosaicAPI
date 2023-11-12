package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.ISubscriberContainer;
import io.github.mosaicmc.mosaicapi.api.ISubscriberRegistry;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@EqualsAndHashCode(callSuper = false)
public final class SubscriberRegistry implements ISubscriberRegistry {
    @Getter
    @EqualsAndHashCode.Exclude
    private final Map<Type<?>, ISubscriberContainer<?>> subscribers;
    private final PluginContainer plugin;

    SubscriberRegistry(PluginContainer plugin) {
        this.plugin = plugin;
        this.subscribers = new ConcurrentHashMap<>();
    }

    @Override
    public <T extends Event<T>> void subscribe(Type<T> event, Consumer<T> consumer) {
        if (subscribers.containsKey(event)) {
            throw new IllegalStateException("Duplicate subscribers for " + event.name() + ". Plugin: " + plugin.id());
        }
        subscribers.put(event, new SubscriberContainer<>(event, consumer, plugin));
    }
}
