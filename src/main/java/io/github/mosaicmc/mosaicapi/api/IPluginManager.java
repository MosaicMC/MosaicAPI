package io.github.mosaicmc.mosaicapi.api;

import com.google.common.collect.BiMap;
import io.github.mosaicmc.mosaicapi.internal.Loader;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface IPluginManager {
    static IPluginManager getInstance() {
        return Loader.getInstance().pluginManager;
    }

    BiMap<String, ? extends IPluginContainer> getPlugins();
}
