package io.github.mosaicmc.mosaicapi.api.event;

public interface Subscriber<T extends Event<T>> {
    void call(T event) throws Throwable;

    <X extends Throwable> void error(X error);

    Class<T> getEventClass();
}
