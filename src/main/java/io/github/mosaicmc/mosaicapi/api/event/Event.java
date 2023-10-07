package io.github.mosaicmc.mosaicapi.api.event;

public abstract class Event<T extends Event<T>> {
    /**
     * Calls the event on all its registered subscribers.
     *
     * @param subs Event subscribers.
     */
    @SuppressWarnings("unchecked")
    public void call(Iterable<Subscriber<T>> subs) {
        for (final var sub : subs) {
            sub.call((T) this);
        }
    }
}
