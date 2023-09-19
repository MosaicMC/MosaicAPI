package io.github.mosaicmc.mosaicapi.event;

import io.github.mosaicmc.mosaicapi.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.util.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract sealed class EventHandler permits EventHandlerImpl {
    public EventRegistryBuilder eventRegistryBuilder() {
        return new EventHandlerImpl.EventRegistryBuilderImpl();
    }

    public ListenerBuilder listenerBuilder() {
        return new EventHandlerImpl.ListenerBuilderImpl();
    }

    protected final Map<Class<?>, List<Subscriber<?>>> events = new HashMap<>();

    public abstract <T extends Event<T>> List<Subscriber<T>> getEvent(Class<T> tClass, boolean check);

    protected abstract <T extends Event<T>> void checkEvent(Class<T> tClass, List<Subscriber<?>> subscribers);

    public abstract <T extends Event<T>> boolean unloadEvent(Class<T> tClass);

    public abstract <T extends Event<T>> void fireEvent(T event);

    public abstract void registerEventRegistry(EventRegistryBuilder eventClasses);

    public abstract void registerListener(ListenerBuilder listener);

    protected abstract <T extends Event<T>> List<Subscriber<T>> castSubscribers(List<Subscriber<?>> subscribers);

    public abstract static sealed class EventRegistryBuilder permits EventHandlerImpl.EventRegistryBuilderImpl {
        protected final List<Class<Event<?>>> events = new ArrayList<>();

        protected EventRegistryBuilder() {}

        public abstract <T extends Event<T>> void registerEvent(Class<T> eventClass);
    }

    public abstract static sealed class ListenerBuilder permits EventHandlerImpl.ListenerBuilderImpl {
        protected final List<Subscriber<?>> subscribers = new ArrayList<>();

        protected ListenerBuilder() {}

        public abstract <T extends Event<T>> void subscribe(
                Class<T> eventClass, ExceptionConsumer<T> consumer, Option<Consumer<? super Throwable>> errorCaller);

        public <T extends Event<T>> void subscribe(Class<T> eventClass, ExceptionConsumer<T> consumer) {
            subscribe(eventClass, consumer, new Option.None<>());
        }
    }
}
