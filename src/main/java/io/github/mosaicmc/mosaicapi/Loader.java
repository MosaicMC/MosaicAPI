package io.github.mosaicmc.mosaicapi;

import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class Loader {
    private static final Init<Loader> INSTANCE = new Init<>();
    public final Logger logger = LoggerFactory.getLogger("MosaicAPI");
    private final MinecraftServer server;

    private Loader(MinecraftServer server) {
        this.server = server;
    }

    public static Loader getInstance() {
        return INSTANCE.get();
    }

    public static Loader loadLoader(MinecraftServer server) {
        INSTANCE.setter(() -> new Loader(server));
        final var $ = INSTANCE.get();
        $.load();
        return $;
    }

    public MinecraftServer getServer() {
        return server;
    }

    private void load() {
        new PluginManager(server);
    }

}
