package io.github.mosaicmc.mosaicapi;

import org.slf4j.Logger;

import java.util.List;

public record PluginContainer(String name, List<PluginEntrypoint> entrypoints, Logger logger) {
}
