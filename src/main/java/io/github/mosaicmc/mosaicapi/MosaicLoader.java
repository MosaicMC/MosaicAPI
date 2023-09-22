package io.github.mosaicmc.mosaicapi;

import io.github.mosaicmc.mosaicapi.api.MosaicServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MosaicLoader {
    private final MosaicServer server;
    private final Logger logger;

    public MosaicLoader(MosaicServer server) {
        this.server = server;
        this.logger = LoggerFactory.getLogger("MosaicAPI");
        onServerLoad();
    }

    private void onServerLoad() {
        logger.info("Loading Plugins...");
    }
}
