package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.event.EventRegistry;
import io.github.mosaicmc.mosaicapi.api.event.SubscriberRegistry;
import io.github.mosaicmc.mosaicapi.util.MutOnce;

public abstract class PluginInitializer {
    protected final MutOnce<PluginContainer> pluginContainer = new MutOnce<>(null);

    public abstract void onEnable(SubscriberRegistry registry);

    public void eventRegister(EventRegistry registry) {
        // For override.
    }

    public final void setPluginContainer(PluginContainer pluginContainer) {
        this.pluginContainer.mutate(pluginContainer);
    }
}
