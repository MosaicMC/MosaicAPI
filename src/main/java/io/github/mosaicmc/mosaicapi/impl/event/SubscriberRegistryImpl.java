package io.github.mosaicmc.mosaicapi.impl.event;

import io.github.mosaicmc.mosaicapi.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.event.Event;
import io.github.mosaicmc.mosaicapi.api.event.Subscriber;
import io.github.mosaicmc.mosaicapi.api.event.SubscriberRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class SubscriberRegistryImpl implements SubscriberRegistry {
    private final List<Subscriber<?>> subscribers;
    private final PluginContainer pluginContainer;

    public SubscriberRegistryImpl(final PluginContainer pluginContainer) {
        this.subscribers = new ArrayList<>();
        this.pluginContainer = pluginContainer;
    }

    @Override
    public <T extends Event<T>> void subscribe(final Class<T> eventClass, final Consumer<T> subscriber) {
        subscribers.add(new SubscriberImpl<>(pluginContainer, eventClass, subscriber));
    }

    @Override
    public PluginContainer getPluginContainer() {
        return pluginContainer;
    }

    @NotNull
    @Override
    public Iterator<Subscriber<?>> iterator() {
        return subscribers.iterator();
    }
}
