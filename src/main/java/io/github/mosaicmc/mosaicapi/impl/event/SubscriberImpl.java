package io.github.mosaicmc.mosaicapi.impl.event;

import io.github.mosaicmc.mosaicapi.api.event.Event;
import io.github.mosaicmc.mosaicapi.api.event.Subscriber;
import io.github.mosaicmc.mosaicapi.api.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.api.util.Option;
import java.util.function.Consumer;

public final class SubscriberImpl<T extends Event<T>> implements Subscriber<T> {
    private final Class<T> eventClass;
    private final ExceptionConsumer<T> caller;
    private final Option<Consumer<? super Throwable>> errorConsumer;

    public SubscriberImpl(
            Class<T> eventClass, ExceptionConsumer<T> caller, Option<Consumer<? super Throwable>> errorConsumer) {
        this.eventClass = eventClass;
        this.caller = caller;
        this.errorConsumer = errorConsumer;
    }

    @Override
    public void call(T event) throws Throwable {
        caller.accept(event);
    }

    @Override
    public <X extends Throwable> void error(X error) {
        if (this.errorConsumer instanceof Option.Some<Consumer<? super Throwable>> some) {
            some.value().accept(error);
        }
    }

    @Override
    public Class<T> getEventClass() {
        return eventClass;
    }
}
