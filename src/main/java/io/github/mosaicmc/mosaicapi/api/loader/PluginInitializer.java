package io.github.mosaicmc.mosaicapi.api.loader;

import io.github.mosaicmc.mosaicapi.api.event.EventRegistryBuilder;
import io.github.mosaicmc.mosaicapi.api.event.ListenerBuilder;

public interface PluginInitializer {
    void init(PluginContainer plugin, ListenerBuilder listenerBuilder);

    default void registerEvents(PluginContainer plugin, EventRegistryBuilder eventHandler) {
        // For override
    }

    default void unload() {
        // For override
    }
}
