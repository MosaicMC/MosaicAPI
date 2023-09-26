package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.PluginContainer;

import java.util.function.Consumer;

public interface SubscriberRegistry extends Iterable<Subscriber<?>> {

    /**
     * Subscribes a consumer to a specific event class.
     *
     * @param eventClass The class of the event to subscribe to.
     * @param subscriber A consumer that will handle the subscribed events.
     * @param <T>        The type of event for which the subscriber is being registered.
     */
    <T extends Event<T>> void subscribe(Class<T> eventClass, Consumer<T> subscriber);

    /**
     * Retrieves the plugin container associated with this registry.
     *
     * @return The {@link PluginContainer} containing the plugin's subscribers.
     */
    PluginContainer getPluginContainer();
}
