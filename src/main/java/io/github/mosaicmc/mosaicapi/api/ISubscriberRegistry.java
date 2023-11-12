package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

@ApiStatus.NonExtendable
public interface ISubscriberRegistry {
    <T extends Event<T>> void subscribe(Type<T> event, Consumer<T> consumer);
}
