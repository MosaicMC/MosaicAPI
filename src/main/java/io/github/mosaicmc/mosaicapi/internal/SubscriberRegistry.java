package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.IPluginRegistry;
import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@ApiStatus.Internal
public final class SubscriberRegistry implements IPluginRegistry {
    private final Map<Type<?>, SubscriberContainer<?>> subscribers;
    private final PluginContainer plugin;

    public SubscriberRegistry(PluginContainer plugin) {
        this.plugin = plugin;
        this.subscribers = new IdentityHashMap<>();
    }

    @Override
    public <T extends Event<T>> void subscribe(Type<T> event, Consumer<T> consumer) {
        if (subscribers.containsKey(event)) {
            Loader.logger.error("Duplicate subscribers for {}. Plugin: {}", event.name(), plugin.id());
            throw new IllegalStateException(plugin.id());
        }
        subscribers.put(event, new SubscriberContainer<>(event, consumer, plugin));
    }

    public Map<Type<?>, SubscriberContainer<?>> getAll() {
        return Map.copyOf(subscribers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriberRegistry that)) return false;
        return Objects.equals(plugin, that.plugin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plugin);
    }
}
