package io.github.mosaicmc.mosaicapi.core.internal;

import io.github.mosaicmc.mosaicapi.core.api.Loader;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Internal class, used for plugin loading.
 */
@Getter
public final class LoaderImpl implements Loader {
    public static final Logger logger = LoggerFactory.getLogger("MosaicMC/Core");
    @Nullable
    private static LoaderImpl INSTANCE;

    private final PluginManagerImpl pluginManager;
    private final EventManagerImpl eventManager;
    private final MinecraftServer server;

    private LoaderImpl(MinecraftServer server) {
        this.server = server;
        this.pluginManager = new PluginManagerImpl();
        this.eventManager = new EventManagerImpl(pluginManager.getPlugins());
        load();
    }

    public static LoaderImpl getInstance() {
        assert INSTANCE != null;
        return INSTANCE;
    }

    public static LoaderImpl initialize(MinecraftServer server) {
        return INSTANCE = new LoaderImpl(server);
    }

    public static boolean isLoaded() {
        return INSTANCE != null;
    }

    private void load() {
        logger.info("Loaded plugins: {}", pluginManager.getPlugins().keySet());
    }
}
