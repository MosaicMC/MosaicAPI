package io.github.mosaicmc.mosaicapi.api;

public abstract class Event<T extends Event<T>> {
    public void call() {

    }
    public abstract String getIdentifier();
}
