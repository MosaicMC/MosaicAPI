package io.github.mosaicmc.mosaicapi.mixins;

import io.github.mosaicmc.mosaicapi.MosaicLoader;
import io.github.mosaicmc.mosaicapi.impl.MosaicServerImpl;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
public final class MinecraftServerMixin {
    @SuppressWarnings("unused")
    @Unique private final MosaicLoader mosaicLoad = new MosaicLoader(new MosaicServerImpl((MinecraftServer) (Object) this));
}
