package io.github.mosaicmc.mosaicapi.impl.event;

import io.github.mosaicmc.mosaicapi.api.event.Event;
import io.github.mosaicmc.mosaicapi.api.event.EventRegistryBuilder;
import java.util.ArrayList;
import java.util.List;

public final class EventRegistryBuilderImpl implements EventRegistryBuilder {
    private final List<Class<Event<?>>> events = new ArrayList<>();

    @Override
    public <T extends Event<T>> void registerEvent(Class<T> eventClass) {
        //noinspection unchecked
        this.events.add((Class<Event<?>>) eventClass);
    }

    @Override
    public List<Class<Event<?>>> getEvents() {
        return events;
    }
}
