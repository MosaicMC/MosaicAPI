package io.github.mosaicmc.mosaicapi.api.mc;

import io.github.mosaicmc.mosaicapi.api.MosaicLoader;

public interface Server {
    int getPlayerAmount();

    MosaicLoader getLoader();
}
