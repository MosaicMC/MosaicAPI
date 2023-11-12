package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.api.ILoader;
import io.github.mosaicmc.mosaicapi.utils.InitHelper;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public final class Loader implements ILoader {
    public static final Logger logger;
    private static final InitHelper<Loader> INSTANCE;

    static {
        logger = LoggerFactory.getLogger("MosaicAPI");
        INSTANCE = new InitHelper<>();
    }

    public final PluginManager pluginManager;
    public final EventManager eventManager;
    private final MinecraftServer server;

    private Loader(MinecraftServer server) {
        this.server = server;
        this.pluginManager = new PluginManager();
        this.eventManager = pluginManager.getEventManager();
        load();
    }

    public static Loader getInstance() {
        return INSTANCE.get();
    }

    public static Loader initialize(MinecraftServer server) {
        return INSTANCE.initialize(new Loader(server));
    }

    public static boolean isLoaded() {
        return INSTANCE.isInitialized();
    }

    private void load() {
        logger.info("Loaded plugins: {}", pluginManager.getPlugins().keySet());
    }
}
