package io.github.mosaicmc.mosaicapi.internal

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import org.slf4j.LoggerFactory

object Main : PreLaunchEntrypoint {
    private val logger = LoggerFactory.getLogger("MosaicAPI")

    override fun onPreLaunch() {
        logger.info("test")
    }
}
