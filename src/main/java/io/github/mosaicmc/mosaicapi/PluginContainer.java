package io.github.mosaicmc.mosaicapi;

import java.util.List;

public record PluginContainer(String name, List<PluginEntrypoint> entrypoints) {
}
