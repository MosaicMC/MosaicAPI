package io.github.mosaicmc.mosaicapi.api;

import com.google.common.collect.BiMap;
import io.github.mosaicmc.mosaicapi.internal.Loader;
import org.jetbrains.annotations.ApiStatus;

/**
 * API interface for the plugin manager.
 */
@ApiStatus.NonExtendable
public interface IPluginManager {
    /**
     * Returns the singleton instance of the plugin manager.
     *
     * @return the plugin manager instance
     */
    static IPluginManager getInstance() {
        return Loader.getInstance().pluginManager;
    }

    /**
     * Returns a bidirectional map of plugin names to plugin containers.
     *
     * @return A bidirectional map of plugin names to plugin containers.
     */
    BiMap<String, IPluginContainer> getPlugins();
}
