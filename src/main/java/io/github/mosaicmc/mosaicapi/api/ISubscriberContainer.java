package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

/**
 * API interface for the subscriber container.
 * @param <T> The type of the event
 */
@ApiStatus.NonExtendable
public interface ISubscriberContainer<T extends Event<T>> {
    /**
     * Retrieves the event of type T.
     *
     * @return The event of type T.
     */
    Type<T> getEvent();

    /**
     * Returns a consumer of type T.
     *
     * @return the consumer of type T
     */
    Consumer<T> getConsumer();
    /**
     * Retrieves the plugin container.
     *
     * @return the plugin container
     */
    IPluginContainer getPlugin();
}
