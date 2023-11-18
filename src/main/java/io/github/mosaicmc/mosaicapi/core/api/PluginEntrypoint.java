package io.github.mosaicmc.mosaicapi.core.api;

import org.jetbrains.annotations.ApiStatus;

/**
 * API interface for the plugin entrypoint.
 */
public abstract class PluginEntrypoint {
    @ApiStatus.Internal
    private PluginContainer plugin;

    protected PluginContainer getPlugin() {
        return this.plugin;
    }

    public abstract void load(PluginContainer plugin);

    @ApiStatus.OverrideOnly
    public void registerSubscribers(SubscriberRegistry sr) {
    }

    @ApiStatus.OverrideOnly
    public void registerEvent(EventRegistry er) {
    }
}
