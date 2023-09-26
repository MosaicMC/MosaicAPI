package io.github.mosaicmc.mosaicapi.api.mc;

public interface Server {
    /**
     * Retrieves the current number of players connected to the server.
     *
     * @return The number of players connected to the server.
     */
    int getPlayerAmount();
}
