package io.github.mosaicmc.mosaicapi.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract sealed class EventHandler permits EventHandlerImpl {
    protected final Map<Class<?>, List<Subscriber<?>>> events = new HashMap<>();

    public EventRegistryBuilder eventRegistryBuilder() {
        return new EventRegistryBuilderImpl();
    }

    public ListenerBuilder listenerBuilder() {
        return new ListenerBuilderImpl();
    }

    public abstract <T extends Event<T>> List<Subscriber<T>> getEvent(Class<T> tClass, boolean check);

    protected abstract <T extends Event<T>> void checkEvent(Class<T> tClass, List<Subscriber<?>> subscribers);

    public abstract <T extends Event<T>> boolean unloadEvent(Class<T> tClass);

    public abstract <T extends Event<T>> void fireEvent(T event);

    public abstract void registerEventRegistry(EventRegistryBuilder eventClasses);

    public abstract void registerListener(ListenerBuilder listener);

    protected abstract <T extends Event<T>> List<Subscriber<T>> castSubscribers(List<Subscriber<?>> subscribers);
}
