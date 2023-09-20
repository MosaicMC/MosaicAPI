package io.github.mosaicmc.mosaicapi.event;

import io.github.mosaicmc.mosaicapi.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.util.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract sealed class ListenerBuilder permits ListenerBuilderImpl {
    protected final List<Subscriber<?>> subscribers = new ArrayList<>();

    protected ListenerBuilder() {}

    public abstract <T extends Event<T>> void subscribe(
            Class<T> eventClass, ExceptionConsumer<T> consumer, Option<Consumer<? super Throwable>> errorCaller);

    public <T extends Event<T>> void subscribe(Class<T> eventClass, ExceptionConsumer<T> consumer) {
        subscribe(eventClass, consumer, new Option.None<>());
    }
}
