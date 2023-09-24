package io.github.mosaicmc.mosaicapi.api.event;

public sealed interface Event<T extends Event<T>> permits AbstractEvent {
    void call(EventManager eventManager);
}
