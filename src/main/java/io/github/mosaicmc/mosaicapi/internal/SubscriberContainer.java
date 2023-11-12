package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.ISubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Consumer;

/**
 * Internal class, used for subscriber registration.
 */
@Data
class SubscriberContainer<T extends Event<T>> implements ISubscriberContainer<T> {
    private final Type<T> event;
    @EqualsAndHashCode.Exclude
    private final Consumer<T> consumer;
    private final PluginContainer plugin;
}
