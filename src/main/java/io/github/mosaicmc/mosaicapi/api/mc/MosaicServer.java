package io.github.mosaicmc.mosaicapi.api.mc;

import io.github.mosaicmc.mosaicapi.api.loader.Loader;

public interface MosaicServer {
    int serverSize();

    Loader getLoader();
}
