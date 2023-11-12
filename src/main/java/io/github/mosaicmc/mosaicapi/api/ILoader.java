package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.internal.Loader;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface ILoader {
    static ILoader getInstance() {
        return Loader.getInstance();
    }

    static boolean isLoaded() {
        return Loader.isLoaded();
    }

    MinecraftServer getServer();
}
