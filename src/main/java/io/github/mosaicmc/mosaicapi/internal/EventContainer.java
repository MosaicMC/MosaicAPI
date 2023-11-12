package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.ISubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * Internal class, used for event registration.
 * @param <T>
 */
@Data
class EventContainer<T extends Event<T>> {
    private final Type<T> type;
    @EqualsAndHashCode.Exclude private final BiConsumer<T, Collection<ISubscriberContainer<T>>> consumer;
    private final PluginContainer plugin;
}
