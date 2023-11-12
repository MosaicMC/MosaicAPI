package io.github.mosaicmc.mosaicapi.api;

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
    public abstract void onLoad(ISubscriberRegistry sr);

    @ApiStatus.OverrideOnly
    public void registerEvent(IEventRegistry er) {

    }
}
