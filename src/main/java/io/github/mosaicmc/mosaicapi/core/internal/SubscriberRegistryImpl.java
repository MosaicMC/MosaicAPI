package io.github.mosaicmc.mosaicapi.core.internal;

import io.github.mosaicmc.mosaicapi.core.api.Event;
import io.github.mosaicmc.mosaicapi.core.api.SubscriberRegistry;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Internal class, used for subscriber registration.
 */
@EqualsAndHashCode(callSuper = false)
final class SubscriberRegistryImpl implements SubscriberRegistry {
    @Getter
    @EqualsAndHashCode.Exclude
    private final Map<Type<?>, SubscriberContainerImpl<?>> subscribers;
    private final PluginContainerImpl plugin;

    SubscriberRegistryImpl(PluginContainerImpl plugin) {
        this.plugin = plugin;
        this.subscribers = new ConcurrentHashMap<>();
    }

    @Override
    public <T extends Event<T>> void subscribe(Type<T> event, Consumer<T> consumer) {
        if (subscribers.containsKey(event)) {
            throw new IllegalStateException("Duplicate subscribers for " + event.getName() + ". Plugin: " + plugin.getId());
        }
        subscribers.put(event, new SubscriberContainerImpl<>(event, consumer, plugin));
    }
}
