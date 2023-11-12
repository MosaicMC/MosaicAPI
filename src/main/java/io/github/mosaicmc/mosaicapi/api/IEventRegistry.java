package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * API interface for the event registry.
 */
@ApiStatus.NonExtendable
public interface IEventRegistry {
    /**
     * Registers an event of type T.
     *
     * @param event The event to register.
     * @param <T> The type of the event.
     */
    <T extends Event<T>> void register(Type<T> event);

    /**
     * Registers an event in parallel.
     *
     * @param <T>   the type of event being registered
     * @param event the event to be registered
     */
    <T extends Event<T>> void registerParallel(Type<T> event);

    /**
     * Registers an event and its consumer.
     *
     * @param event    the event type to register
     * @param consumer the consumer that handles the event
     * @param <T>      the type of the event
     */
    <T extends Event<T>> void register(Type<T> event, BiConsumer<T, Collection<ISubscriberContainer<T>>> consumer);
}
