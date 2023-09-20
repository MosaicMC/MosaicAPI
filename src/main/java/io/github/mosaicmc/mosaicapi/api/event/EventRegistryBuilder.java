package io.github.mosaicmc.mosaicapi.api.event;

import java.util.List;

public interface EventRegistryBuilder {
    <T extends Event<T>> void registerEvent(Class<T> eventClass);

    List<Class<Event<?>>> getEvents();
}
