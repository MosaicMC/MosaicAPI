package io.github.mosaicmc.mosaicapi.event;

import io.github.mosaicmc.mosaicapi.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.util.Option;
import java.util.function.Consumer;

public abstract sealed class Subscriber<T extends Event<T>> permits SubscriberImpl {
    public final Class<T> eventClass;
    protected final ExceptionConsumer<T> caller;
    protected final Option<Consumer<? super Throwable>> errorConsumer;

    public Subscriber(Class<T> tClass, ExceptionConsumer<T> caller, Option<Consumer<? super Throwable>> errorConsumer) {
        this.eventClass = tClass;
        this.caller = caller;
        this.errorConsumer = errorConsumer;
    }

    public abstract void call(T event) throws Throwable;

    public abstract <X extends Throwable> void error(X error);
}
