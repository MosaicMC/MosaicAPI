package io.github.mosaicmc.mosaicapi.api.event;

public sealed interface Event<T extends Event<T>> permits AbstractEvent {

    /**
     * Calls the event on all its registered subscribers.
     *
     * @param eventManager The event manager responsible for managing event subscribers.
     */
    void call(EventManager eventManager);
}
