package io.github.mosaicmc.mosaicapi.api.event;

public abstract non-sealed class AbstractEvent<T extends Event<T>> implements Event<T> {
    @Override
    public void call(EventManager eventManager) {
        final var subs = eventManager.getEventSubscribers((Class<T>) getClass());
        for (final var sub : subs) {
            sub.call((T) this);
        }
    }
}
