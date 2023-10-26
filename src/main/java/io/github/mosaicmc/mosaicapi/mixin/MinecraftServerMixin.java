package io.github.mosaicmc.mosaicapi.mixin;

import io.github.mosaicmc.mosaicapi.Loader;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @SuppressWarnings("unused")
    @Unique
    private final Loader loader = Loader.loadLoader((MinecraftServer) (Object) this);
}
