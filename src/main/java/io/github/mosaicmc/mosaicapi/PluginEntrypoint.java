package io.github.mosaicmc.mosaicapi;

public abstract class PluginEntrypoint {
    public final Init<PluginContainer> plugin = new Init<>();

    abstract void load();

    protected final PluginContainer getPlugin() {
        return plugin.get();
    }
}
