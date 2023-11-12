package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

/**
 * API interface for the subscriber registry.
 */
@ApiStatus.NonExtendable
public interface ISubscriberRegistry {
    /**
     * Subscribes a consumer to a specific event type.
     *
     * @param event    The event type to subscribe to.
     * @param consumer The consumer to be called when the event occurs.
     * @param <T>      The type of the event.
     */
    <T extends Event<T>> void subscribe(Type<T> event, Consumer<T> consumer);
}
