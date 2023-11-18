package io.github.mosaicmc.mosaicapi.core.internal;

import io.github.mosaicmc.mosaicapi.core.api.Event;
import io.github.mosaicmc.mosaicapi.core.api.SubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.function.BiConsumer;

/**
 * Internal class, used for event registration.
 *
 * @param <T>
 */
@Data
final class EventContainerImpl<T extends Event<T>> {
    private final Type<T> type;
    @EqualsAndHashCode.Exclude
    private final BiConsumer<T, Set<SubscriberContainer<T>>> consumer;
    private final PluginContainerImpl plugin;
}
