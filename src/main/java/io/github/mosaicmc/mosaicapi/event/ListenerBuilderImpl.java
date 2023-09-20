package io.github.mosaicmc.mosaicapi.event;

import io.github.mosaicmc.mosaicapi.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.util.Option;
import java.util.function.Consumer;

final class ListenerBuilderImpl extends ListenerBuilder {

    @Override
    public <T extends Event<T>> void subscribe(
            Class<T> eventClass, ExceptionConsumer<T> consumer, Option<Consumer<? super Throwable>> errorCaller) {
        this.subscribers.add(new SubscriberImpl<>(eventClass, consumer, errorCaller));
    }
}
