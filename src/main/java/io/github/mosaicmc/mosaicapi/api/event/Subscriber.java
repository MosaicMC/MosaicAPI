package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.PluginContainer;

public interface Subscriber<T extends Event<T>> {
    PluginContainer getPluginContainer();
    Class<T> getEventClass();
    void call(T event);
}
