package io.github.mosaicmc.mosaicapi.loader;

import io.github.mosaicmc.mosaicapi.event.EventHandler;

public interface PluginInitializer {
    void init(PluginContainer plugin, EventHandler.ListenerBuilder listenerBuilder);

    default void registerEvents(PluginContainer plugin, EventHandler.EventRegistryBuilder eventHandler) {
        // For override
    }

    default void unload() {
        // For override
    }
}
