package io.github.mosaicmc.mosaicapi.core.api;

import com.google.common.collect.BiMap;
import io.github.mosaicmc.mosaicapi.core.internal.LoaderImpl;
import org.jetbrains.annotations.ApiStatus;

/**
 * API interface for the plugin manager.
 */
@ApiStatus.NonExtendable
public interface PluginManager {
    /**
     * Returns the singleton instance of the plugin manager.
     *
     * @return the plugin manager instance
     */
    static PluginManager getInstance() {
        return LoaderImpl.getInstance().getPluginManager();
    }

    /**
     * Returns a bidirectional map of plugin names to plugin containers.
     *
     * @return A bidirectional map of plugin names to plugin containers.
     */
    BiMap<String, PluginContainer> getPlugins();
}
