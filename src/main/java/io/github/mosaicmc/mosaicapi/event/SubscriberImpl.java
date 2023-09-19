package io.github.mosaicmc.mosaicapi.event;

import io.github.mosaicmc.mosaicapi.util.ExceptionConsumer;
import io.github.mosaicmc.mosaicapi.util.Option;
import java.util.function.Consumer;

public final class SubscriberImpl<T extends Event<T>> extends Subscriber<T> {
    public SubscriberImpl(Class<T> tClass, ExceptionConsumer<T> caller, Option<Consumer<? super Throwable>> onError) {
        super(tClass, caller, onError);
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
}
