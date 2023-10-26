package io.github.mosaicmc.mosaicapi;

public abstract class PluginEntrypoint {
    protected final Init<PluginContainer> container = new Init<>();

    abstract void load();
}
