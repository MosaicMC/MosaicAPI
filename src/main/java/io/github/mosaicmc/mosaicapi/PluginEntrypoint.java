package io.github.mosaicmc.mosaicapi;

import io.github.mosaicmc.mosaicapi.utils.Init;
import org.jetbrains.annotations.ApiStatus;
import oshi.annotation.concurrent.ThreadSafe;

public abstract class PluginEntrypoint {
    public final Init<PluginContainer> plugin = new Init<>();

    @ApiStatus.OverrideOnly
    @ThreadSafe
    abstract void load();

    protected final PluginContainer getPlugin() {
        return plugin.get();
    }
}
