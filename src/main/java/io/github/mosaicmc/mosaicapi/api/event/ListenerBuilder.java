package io.github.mosaicmc.mosaicapi.api.event;

import io.github.mosaicmc.mosaicapi.api.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.api.util.Option;
import java.util.List;
import java.util.function.Consumer;

public interface ListenerBuilder {
    <T extends Event<T>> void subscribe(
            Class<T> eventClass, ExceptionConsumer<T> consumer, Option<Consumer<? super Throwable>> errorCaller);

    default <T extends Event<T>> void subscribe(Class<T> eventClass, ExceptionConsumer<T> consumer) {
        subscribe(eventClass, consumer, new Option.None<>());
    }

    List<Subscriber<?>> getSubscribers();
}
