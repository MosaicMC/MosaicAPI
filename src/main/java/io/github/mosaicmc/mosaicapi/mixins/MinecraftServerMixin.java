package io.github.mosaicmc.mosaicapi.mixins;

import io.github.mosaicmc.mosaicapi.api.MosaicLoader;
import io.github.mosaicmc.mosaicapi.impl.MosaicLoaderImpl;
import io.github.mosaicmc.mosaicapi.impl.mc.MosaicServer;
import io.github.mosaicmc.mosaicapi.util.ServerHelper;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
public final class MinecraftServerMixin implements ServerHelper {
    @SuppressWarnings("unused")
    @Unique
    private final MosaicLoader mosaicLoad = new MosaicLoaderImpl(new MosaicServer((MinecraftServer) (Object) this));

    @Override
    public MosaicLoader mosaicAPI$getLoader() {
        return mosaicLoad;
    }
}
