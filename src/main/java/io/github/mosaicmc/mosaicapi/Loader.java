package io.github.mosaicmc.mosaicapi;

import io.github.mosaicmc.mosaicapi.utils.Init;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;


public final class Loader {
    public static final Logger logger = LoggerFactory.getLogger("MosaicAPI");
    private static final Init<Loader> INSTANCE = new Init<>();
    private final MinecraftServer server;
    private final Init<PluginManager> pluginManager;

    private Loader(MinecraftServer server) {
        this.server = server;
        this.pluginManager = new Init<>();
    }

    public static Loader getInstance() {
        return INSTANCE.get();
    }

    @ApiStatus.Internal
    public static Loader loadLoader(MinecraftServer server) {
        requireNonNull(server);
        final var instance = INSTANCE.init(() -> new Loader(server));
        instance.load();
        return instance;
    }

    public MinecraftServer getServer() {
        return server;
    }

    private void load() {
        this.pluginManager.init(PluginManager::new);
        final var pluginManager = this.pluginManager.get();
        pluginManager.preloadPlugins();
        pluginManager.loadPlugins();
        logger.info("Loaded plugins: {}", pluginManager.getPlugins().size());
    }

}
