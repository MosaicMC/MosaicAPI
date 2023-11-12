package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.ISubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;

import java.util.Collection;
import java.util.function.BiConsumer;

record EventContainer<T extends Event<T>>(
        Type<T> type,
        BiConsumer<T, Collection<ISubscriberContainer<T>>> consumer,
        PluginContainer plugin
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventContainer<?> that = (EventContainer<?>) o;

        if (!type.equals(that.type)) return false;
        return plugin.equals(that.plugin);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + plugin.hashCode();
        return result;
    }
}
