package io.github.mosaicmc.mosaicapi.core.api;

import io.github.mosaicmc.mosaicapi.core.internal.LoaderImpl;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;

/**
 * API interface for the loader.
 */
@ApiStatus.NonExtendable
public interface Loader {
    /**
     * Returns an instance of the ILoader interface.
     *
     * @return An instance of the ILoader interface.
     */
    static Loader getInstance() {
        return LoaderImpl.getInstance();
    }

    /**
     * Check if the loader is currently loaded.
     *
     * @return true if the loader is loaded, false otherwise.
     */
    static boolean isLoaded() {
        return LoaderImpl.isLoaded();
    }

    /**
     * Retrieves the Minecraft server instance.
     *
     * @return The MinecraftServer instance.
     */
    MinecraftServer getServer();
}
