package io.github.mosaicmc.mosaicapi;

import io.github.mosaicmc.mosaicapi.utils.Option;
import net.minecraft.server.MinecraftServer;

public final class Loader {
    private static final Loader INSTANCE = new Loader();
    private Option<MinecraftServer> server = Option.None.get();

    private Loader() {
    }

    public static Loader getInstance() {
        return INSTANCE;
    }

    public void load(MinecraftServer server) {
        switch (this.server) {
            case Option.None<MinecraftServer> $ -> this.server = new Option.Ok<>(server);
            case Option.Ok(MinecraftServer $) -> throw new IllegalStateException("Already loaded!");
        }
    }
}
