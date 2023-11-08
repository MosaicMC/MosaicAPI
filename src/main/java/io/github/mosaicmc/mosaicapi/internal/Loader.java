package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.ILoader;
import io.github.mosaicmc.mosaicapi.utils.InitHelper;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApiStatus.Internal
public final class Loader implements ILoader {
    public static final Logger logger;
    private static final InitHelper<Loader> INSTANCE;

    static {
        logger = LoggerFactory.getLogger("MosaicAPI");
        INSTANCE = new InitHelper<>();
    }

    private final MinecraftServer server;
    private final InitHelper<PluginManager> pluginManager;

    private final InitHelper<EventManager> eventManager;

    private Loader(MinecraftServer server) {
        this.server = server;
        this.pluginManager = new InitHelper<>();
        this.eventManager = new InitHelper<>();
    }

    public static Loader getInstance() {
        return INSTANCE.get();
    }

    public static Loader initialize(MinecraftServer server) {
        return INSTANCE.initialize(() -> new Loader(server).load());
    }

    @Override
    public MinecraftServer getServer() {
        return server;
    }

    private Loader load() {
        pluginManager.initialize(PluginManager::initialize);
        eventManager.initialize(pluginManager.get()::getEventManager);
        logger.info("Loaded plugins: {}", pluginManager.get().getPlugins().keySet());
        return this;
    }
}
