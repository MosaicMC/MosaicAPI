package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.Objects;
import java.util.function.Consumer;

@ApiStatus.Internal
public record SubscriberContainer<T extends Event<T>>(
        Type<T> event,
        Consumer<T> consumer,
        PluginContainer plugin
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriberContainer<?> that)) return false;
        return event == that.event && Objects.equals(plugin, that.plugin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, plugin);
    }
}
