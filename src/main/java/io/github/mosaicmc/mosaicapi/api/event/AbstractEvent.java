package io.github.mosaicmc.mosaicapi.api.event;

public abstract non-sealed class AbstractEvent<T extends Event<T>> implements Event<T> {
    /**
     * Calls the event on all its registered subscribers.
     *
     * @param eventManager The event manager responsible for managing event subscribers.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void call(EventManager eventManager) {
        Iterable<Subscriber<T>> subs = eventManager.getEventSubscribers(getClass());
        for (final var sub : subs) {
            sub.call((T) this);
        }
    }
}
