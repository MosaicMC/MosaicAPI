package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.PluginContainer;

public interface EventRegistry extends Iterable<Class<Event<?>>> {

    /**
     * Registers an event class with the registry.
     *
     * @param eventClass The class of the event to register.
     * @param <T>        The type of event being registered.
     */
    <T extends Event<T>> void registerEvent(Class<T> eventClass);

    /**
     * Retrieves the plugin container associated with this registry.
     *
     * @return The {@link PluginContainer} containing the plugin's events.
     */
    PluginContainer getPluginContainer();
}

