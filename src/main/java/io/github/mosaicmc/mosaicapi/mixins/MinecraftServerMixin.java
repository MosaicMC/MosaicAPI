package io.github.mosaicmc.mosaicapi.mixins;

import io.github.mosaicmc.mosaicapi.Loader;
import io.github.mosaicmc.mosaicapi.LoaderImpl;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
public final class MinecraftServerMixin {
    @Unique public Loader loader = new LoaderImpl((MinecraftServer) (Object) this);
}
