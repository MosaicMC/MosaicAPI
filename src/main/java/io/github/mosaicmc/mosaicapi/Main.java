package io.github.mosaicmc.mosaicapi;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public final class Main implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        //        val plugins = mapOf("MosaicAPI" to "1.0.0", "MosaicMC" to "1.0.0")
        //
        //        val pluginsString =
        //                """
        //        Loading ${plugins.size} ${if (plugins.size > 1) "plugins" else "plugin"}:
        //            - ${plugins.entries.joinToString("\n    - ") { "${it.key} ${it.value}" }}
        //        """
        //                        .trimIndent()
        //        logger.info(pluginsString)

    }
}
