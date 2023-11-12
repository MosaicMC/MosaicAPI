package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.internal.Loader;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;

/**
 * API interface for the loader.
 */
@ApiStatus.NonExtendable
public interface ILoader {
    /**
     * Returns an instance of the ILoader interface.
     *
     * @return An instance of the ILoader interface.
     */
    static ILoader getInstance() {
        return Loader.getInstance();
    }

    /**
     * Check if the loader is currently loaded.
     *
     * @return true if the loader is loaded, false otherwise.
     */
    static boolean isLoaded() {
        return Loader.isLoaded();
    }

    /**
     * Retrieves the Minecraft server instance.
     *
     * @return The MinecraftServer instance.
     */
    MinecraftServer getServer();
}
