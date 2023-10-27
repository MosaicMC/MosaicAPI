package io.github.mosaicmc.mosaicapi;

import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;


public final class Loader {
    private static final Init<Loader> INSTANCE = new Init<>();
    public final Logger logger;
    private final MinecraftServer server;
    private @Nullable PluginManager pluginManager;

    private Loader(MinecraftServer server) {
        this.server = server;
        this.logger = LoggerFactory.getLogger("MosaicAPI");
    }

    public static Loader getInstance() {
        return INSTANCE.get();
    }

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
        this.pluginManager = new PluginManager();
        this.pluginManager.preloadPlugins();
        final var plugins = this.pluginManager.getPlugins();
        for (final var plugin : plugins.values()) {
            for (final var entrypoint : plugin.entrypoints()) {
                entrypoint.plugin.init(plugin);
                entrypoint.load();
            }
        }
        logger.info("Loaded plugins: {}", plugins.size());
    }

}
