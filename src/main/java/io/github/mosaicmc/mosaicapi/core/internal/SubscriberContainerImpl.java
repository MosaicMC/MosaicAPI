package io.github.mosaicmc.mosaicapi.core.internal;

import io.github.mosaicmc.mosaicapi.core.api.Event;
import io.github.mosaicmc.mosaicapi.core.api.SubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Consumer;

/**
 * Internal class, used for subscriber registration.
 */
@Data
final class SubscriberContainerImpl<T extends Event<T>> implements SubscriberContainer<T> {
    private final Type<T> event;
    @EqualsAndHashCode.Exclude
    private final Consumer<T> consumer;
    private final PluginContainerImpl plugin;
}
