package io.github.mosaicmc.mosaicapi.mixins;

import io.github.mosaicmc.mosaicapi.impl.MosaicLoaderImpl;
import io.github.mosaicmc.mosaicapi.impl.mc.MosaicServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
public final class MinecraftServerMixin {
    @SuppressWarnings("unused")
    @Unique private final MosaicLoaderImpl mosaicLoad = new MosaicLoaderImpl(new MosaicServer((MinecraftServer) (Object) this));
}
