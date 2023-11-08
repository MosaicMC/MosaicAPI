package io.github.mosaicmc.mosaicapi.mixin;

import io.github.mosaicmc.mosaicapi.internal.Loader;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
@ApiStatus.Internal
public abstract class MinecraftServerMixin {
    @SuppressWarnings("unused")
    @Unique
    private final Loader loader = Loader.initialize(MinecraftServer.class.cast(this));
}
