package io.github.mosaicmc.mosaicapi.mixins;

import io.github.mosaicmc.mosaicapi.api.mc.MosaicServer;
import io.github.mosaicmc.mosaicapi.impl.mc.MosaicServerImpl;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
public final class MinecraftServerMixin {
    @SuppressWarnings("unused")
    @Unique private final MosaicServer mosaicServer = new MosaicServerImpl((MinecraftServer) (Object) this);
}
