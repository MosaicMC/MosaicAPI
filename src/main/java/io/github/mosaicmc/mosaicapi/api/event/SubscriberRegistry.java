package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.PluginContainer;

import java.util.function.Consumer;

public interface SubscriberRegistry extends Iterable<Subscriber<?>> {
    <T extends Event<T>> void subscribe(Class<T> eventClass, Consumer<T> subscriber);
    PluginContainer getPluginContainer();
}
