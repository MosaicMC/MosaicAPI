package io.github.mosaicmc.mosaicapi.api.loader;

public interface PluginContainerBuilder {
    void add(PluginInitializer init);

    PluginContainer build();
}
