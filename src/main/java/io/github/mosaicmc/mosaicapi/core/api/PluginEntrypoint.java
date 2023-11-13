package io.github.mosaicmc.mosaicapi.core.api;

import io.github.mosaicmc.mosaicapi.utils.InitHelper;
import org.jetbrains.annotations.ApiStatus;

/**
 * API interface for the plugin entrypoint.
 */
public abstract class PluginEntrypoint {
    @ApiStatus.Internal
    public final InitHelper<PluginContainer> plugin;

    protected PluginEntrypoint() {
        this.plugin = new InitHelper<>();
    }

    /**
     * Returns the plugin container.
     *
     * @return The plugin container.
     */
    public final PluginContainer getPlugin() {
        return plugin.get();
    }

    /**
     * This method is called when the class implementing this method is loaded.
     * It registers the subscriber registry with the given ISubscriberRegistry object.
     *
     * @param sr The ISubscriberRegistry object to register with.
     */
    @ApiStatus.OverrideOnly
    public abstract void onLoad(SubscriberRegistry sr);

    /**
     * Registers the given event registry.
     *
     * @param er the event registry to register
     */
    @ApiStatus.OverrideOnly
    public void registerEvent(EventRegistry er) {
        // Implement event registration logic
    }
}
