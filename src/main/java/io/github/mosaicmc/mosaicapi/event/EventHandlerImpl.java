package io.github.mosaicmc.mosaicapi.event;

import io.github.mosaicmc.mosaicapi.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.util.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class EventHandlerImpl extends EventHandler {
    @Override
    public <T extends Event<T>> List<Subscriber<T>> getEvent(Class<T> tClass, boolean check) {
        if (!events.containsKey(tClass)) {
            throw new IllegalArgumentException("No event registered for " + tClass.getSimpleName());
        }
        final var subscribers = events.get(tClass);

        if (check) {
            checkEvent(tClass, subscribers);
        }
        return List.copyOf(castSubscribers(events.get(tClass)));
    }

    @Override
    protected <T extends Event<T>> void checkEvent(Class<T> tClass, List<Subscriber<?>> subscribers) {
        for (var sub : subscribers) {
            if (sub == null) {
                throw new NullPointerException();
            }
            if (sub.eventClass != tClass) {
                throw new ClassCastException();
            }
        }
    }

    @Override
    public <T extends Event<T>> boolean unloadEvent(Class<T> tClass) {
        if (!events.containsKey(tClass)) {
            return false;
        }
        return events.remove(tClass) != null;
    }

    @Override
    public <T extends Event<T>> void fireEvent(T event) {
        //noinspection unchecked
        final List<Subscriber<T>> subscribers = getEvent(event.getClass(), false);
        subscribers.forEach(sub -> {
            try {
                sub.call(event);
            } catch (Throwable e) {
                sub.error(e);
            }
        });
    }

    @Override
    public void registerEventRegistry(EventRegistryBuilder eventRegistryBuilder) {
        for (final var clazz : eventRegistryBuilder.events) {
            if (events.containsKey(clazz)) {
                throw new IllegalArgumentException("Event already registered");
            }
            events.put(clazz, new ArrayList<>());
        }
    }

    @Override
    public void registerListener(ListenerBuilder listenerBuilder) {
        for (final var sub : listenerBuilder.subscribers) {
            final var clazz = sub.eventClass;
            if (!events.containsKey(clazz)) {
                throw new IllegalArgumentException("Event " + clazz.getSimpleName() + " is not registered");
            }
            events.get(clazz).add(sub);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends Event<T>> List<Subscriber<T>> castSubscribers(List<Subscriber<?>> subscribers) {
        return (List<Subscriber<T>>) (Object) subscribers;
    }

    static final class EventRegistryBuilderImpl extends EventRegistryBuilder {

        @Override
        public <T extends Event<T>> void registerEvent(Class<T> eventClass) {
            //noinspection unchecked
            this.events.add((Class<Event<?>>) eventClass);
        }
    }

    static final class ListenerBuilderImpl extends ListenerBuilder {

        @Override
        public <T extends Event<T>> void subscribe(
                Class<T> eventClass, ExceptionConsumer<T> consumer, Option<Consumer<? super Throwable>> errorCaller) {
            this.subscribers.add(new SubscriberImpl<>(eventClass, consumer, errorCaller));
        }
    }
}
