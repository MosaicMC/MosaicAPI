package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.api.event.EventRegistry;
import io.github.mosaicmc.mosaicapi.api.event.SubscriberRegistry;
import io.github.mosaicmc.mosaicapi.util.MutOnce;


public abstract class PluginInitializer {

    /**
     * The plugin container associated with this initializer.
     */
    protected final MutOnce<PluginContainer> pluginContainer = new MutOnce<>(null);

    /**
     * Called when the plugin is enabled. Subclasses should override this method
     * to perform plugin-specific initialization.
     *
     * @param registry The {@link SubscriberRegistry} for registering subscribers.
     */
    public abstract void onEnable(SubscriberRegistry registry);

    /**
     * Registers events for the plugin. Subclasses may override this method to
     * define event registration logic.
     *
     * @param registry The {@link EventRegistry} for registering events.
     */
    public void eventRegister(EventRegistry registry) {
        // For override.
    }

    /**
     * Sets the associated {@link PluginContainer} for this initializer.
     *
     * @param pluginContainer The plugin container to associate with this initializer.
     */
    public final void setPluginContainer(PluginContainer pluginContainer) {
        this.pluginContainer.mutate(pluginContainer);
    }
}

