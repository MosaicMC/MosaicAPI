package io.github.mosaicmc.mosaicapi.api.loader;

import io.github.mosaicmc.mosaicapi.api.event.Event;
import io.github.mosaicmc.mosaicapi.impl.loader.LoaderImpl;

public interface Loader {
    static Loader getInstance() {
        if (LoaderImpl.INSTANCE == null) {
            throw new NullPointerException("Accessing Loader before initialization");
        }
        return LoaderImpl.INSTANCE;
    }

    void unloadPlugin(String id);

    default void unloadPlugin(PluginContainer pluginContainer) {
        unloadPlugin(pluginContainer.getId());
    }

    <T extends Event<T>> void fireEvent(T event);
}
