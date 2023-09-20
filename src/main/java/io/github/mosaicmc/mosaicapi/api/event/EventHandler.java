package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.impl.event.EventRegistryBuilderImpl;
import io.github.mosaicmc.mosaicapi.impl.event.ListenerBuilderImpl;
import java.util.List;

public interface EventHandler {
    default EventRegistryBuilder eventRegistryBuilder() {
        return new EventRegistryBuilderImpl();
    }

    default ListenerBuilder listenerBuilder() {
        return new ListenerBuilderImpl();
    }

    <T extends Event<T>> List<Subscriber<T>> getEvent(Class<T> tClass, boolean check);

    <T extends Event<T>> boolean unloadEvent(Class<T> tClass);

    <T extends Event<T>> void fireEvent(T event);

    void registerEventRegistry(EventRegistryBuilder eventClasses);

    void registerListener(ListenerBuilder listener);
}
