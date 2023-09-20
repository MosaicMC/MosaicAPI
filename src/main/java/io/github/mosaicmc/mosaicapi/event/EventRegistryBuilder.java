package io.github.mosaicmc.mosaicapi.event;

import java.util.ArrayList;
import java.util.List;

public abstract sealed class EventRegistryBuilder permits EventRegistryBuilderImpl {
    protected final List<Class<Event<?>>> events = new ArrayList<>();

    protected EventRegistryBuilder() {}

    public abstract <T extends Event<T>> void registerEvent(Class<T> eventClass);
}
