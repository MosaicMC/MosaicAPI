package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

@ApiStatus.NonExtendable
public interface ISubscriberContainer<T extends Event<T>> {
    Type<T> getEvent();

    Consumer<T> getConsumer();

    IPluginContainer getPlugin();
}
