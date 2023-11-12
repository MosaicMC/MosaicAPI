package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.internal.Loader;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface IEventManager {
    static IEventManager getInstance() {
        return Loader.getInstance().eventManager;
    }

    <T extends Event<T>> void callEvent(T event);
}
