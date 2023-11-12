package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.api.ISubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Getter;

import java.util.function.Consumer;

@SuppressWarnings("LombokGetterMayBeUsed")
record SubscriberContainer<T extends Event<T>>(
        @Getter Type<T> event,
        @Getter Consumer<T> consumer,
        @Getter PluginContainer plugin
) implements ISubscriberContainer<T> {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriberContainer<?> that = (SubscriberContainer<?>) o;

        if (!event.equals(that.event)) return false;
        return plugin.equals(that.plugin);
    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + plugin.hashCode();
        return result;
    }
}
