package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.function.BiConsumer;

@ApiStatus.NonExtendable
public interface IEventRegistry {
    <T extends Event<T>> void register(Type<T> event);

    <T extends Event<T>> void registerParallel(Type<T> event);

    <T extends Event<T>> void register(Type<T> event, BiConsumer<T, Collection<ISubscriberContainer<T>>> consumer);
}
