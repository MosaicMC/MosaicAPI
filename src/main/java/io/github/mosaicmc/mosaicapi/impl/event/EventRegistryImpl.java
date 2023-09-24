package io.github.mosaicmc.mosaicapi.impl.event;

import io.github.mosaicmc.mosaicapi.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.event.Event;
import io.github.mosaicmc.mosaicapi.api.event.EventRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventRegistryImpl implements EventRegistry {
    private final List<Class<Event<?>>> events;
    private final PluginContainer container;

    public EventRegistryImpl(final PluginContainer container) {
        this.events = new ArrayList<>();
        this.container = container;
    }

    @Override
    public <T extends Event<T>> void registerEvent(final Class<T> eventClass) {
        //noinspection unchecked
        events.add((Class<Event<?>>) eventClass);
    }

    @Override
    public PluginContainer getPluginContainer() {
        return container;
    }

    @NotNull
    @Override
    public Iterator<Class<Event<?>>> iterator() {
        return events.iterator();
    }
}
