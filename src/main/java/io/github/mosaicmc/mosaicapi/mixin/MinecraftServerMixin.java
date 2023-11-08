package io.github.mosaicmc.mosaicapi.mixin;

import io.github.mosaicmc.mosaicapi.internal.Loader;
import io.github.mosaicmc.mosaicapi.test.TestEvent;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
@ApiStatus.Internal
public abstract class MinecraftServerMixin {
    @SuppressWarnings("unused")
    @Unique
    private final Loader loader = Loader.initialize(MinecraftServer.class.cast(this));

    @Inject(at = @At("HEAD"), method = "isOnlineMode")
    public void isRunning(CallbackInfoReturnable<Boolean> cir) {
        loader.getEventManager().callEvent(new TestEvent());
    }

//    public MinecraftServerMixin(Thread thread, LevelStorage.Session session, ResourcePackManager resourcePackManager, SaveLoader saveLoader, Proxy proxy, DataFixer dataFixer, ApiServices apiServices, WorldGenerationProgressListenerFactory worldGenerationProgressListenerFactory) {
//        super(thread, session, resourcePackManager, saveLoader, proxy, dataFixer, apiServices, worldGenerationProgressListenerFactory);
//    }
}
