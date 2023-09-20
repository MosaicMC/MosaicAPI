package io.github.mosaicmc.mosaicapi.impl.event;

import io.github.mosaicmc.mosaicapi.api.event.Event;
import io.github.mosaicmc.mosaicapi.api.event.ListenerBuilder;
import io.github.mosaicmc.mosaicapi.api.event.Subscriber;
import io.github.mosaicmc.mosaicapi.api.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.api.util.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class ListenerBuilderImpl implements ListenerBuilder {
    private final List<Subscriber<?>> subscribers = new ArrayList<>();

    @Override
    public <T extends Event<T>> void subscribe(
            Class<T> eventClass, ExceptionConsumer<T> consumer, Option<Consumer<? super Throwable>> errorCaller) {
        this.subscribers.add(new SubscriberImpl<>(eventClass, consumer, errorCaller));
    }

    @Override
    public List<Subscriber<?>> getSubscribers() {
        return subscribers;
    }
}
