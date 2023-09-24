package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.PluginContainer;

public interface EventRegistry extends Iterable<Class<Event<?>>> {
    <T extends Event<T>> void registerEvent(Class<T> eventClass);

    PluginContainer getPluginContainer();
}
