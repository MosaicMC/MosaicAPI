package io.github.mosaicmc.mosaicapi.mixin;

import io.github.mosaicmc.mosaicapi.internal.Loader;
import net.minecraft.server.Main;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
@ApiStatus.Internal
public abstract class MainMixin {
    @Inject(at = @At("HEAD"), method = "main")
    private static void mainMethod(String[] args, CallbackInfo info) {
        Loader.logger.info("Starting server with MosaicAPI!");
    }
}
