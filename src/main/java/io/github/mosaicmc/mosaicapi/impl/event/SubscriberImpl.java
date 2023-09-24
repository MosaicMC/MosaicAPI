package io.github.mosaicmc.mosaicapi.impl.event;

import io.github.mosaicmc.mosaicapi.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.event.Event;
import io.github.mosaicmc.mosaicapi.api.event.Subscriber;

import java.util.function.Consumer;

public class SubscriberImpl<T extends Event<T>> implements Subscriber<T> {
    private final PluginContainer pluginContainer;
    private final Class<T> eventClass;
    private final Consumer<T> subscriber;

    public SubscriberImpl(final PluginContainer pluginContainer, final Class<T> eventClass, final Consumer<T> subscriber) {
        this.pluginContainer = pluginContainer;
        this.eventClass = eventClass;
        this.subscriber = subscriber;
    }

    @Override
    public PluginContainer getPluginContainer() {
        return pluginContainer;
    }

    @Override
    public Class<T> getEventClass() {
        return eventClass;
    }

    @Override
    public void call(final T event) {
        subscriber.accept(event);
    }
}
