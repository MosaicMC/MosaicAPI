package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

@ApiStatus.Internal
public record EventContainer<T extends Event<T>>(
        Type<T> type,
        BiConsumer<T, Set<SubscriberContainer<T>>> callback,
        PluginContainer plugin
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventContainer<?> eventContainer)) return false;
        return type == eventContainer.type && Objects.equals(plugin, eventContainer.plugin);
    }
}
