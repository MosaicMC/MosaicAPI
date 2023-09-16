package io.github.mosaicmc.mosaicapi.mixins;

import io.github.mosaicmc.mosaicapi.loader.Loader;
import io.github.mosaicmc.mosaicapi.mc.MosaicServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
public final class MinecraftServerMixin {
    @Unique private final MosaicServer mosaicServer = MosaicServer.of((MinecraftServer) (Object) this);

    @Unique private final Loader loader = Loader.of(mosaicServer);
}
