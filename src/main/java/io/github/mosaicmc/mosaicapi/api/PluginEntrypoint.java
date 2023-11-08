package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.internal.EventRegistry;
import io.github.mosaicmc.mosaicapi.internal.SubscriberRegistry;
import io.github.mosaicmc.mosaicapi.utils.InitHelper;
import org.jetbrains.annotations.ApiStatus;

public abstract class PluginEntrypoint {
    @ApiStatus.Internal
    public final InitHelper<IPluginContainer> plugin;

    protected PluginEntrypoint() {
        this.plugin = new InitHelper<>();
    }

    public final IPluginContainer getPlugin() {
        return plugin.get();
    }

    @ApiStatus.OverrideOnly
    public abstract void onLoad(SubscriberRegistry sr);

    @ApiStatus.OverrideOnly
    public void registerEvent(EventRegistry er) {

    }
}
