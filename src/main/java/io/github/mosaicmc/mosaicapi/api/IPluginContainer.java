package io.github.mosaicmc.mosaicapi.api;

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;

@ApiStatus.NonExtendable
public interface IPluginContainer {
    String getId();

    Logger getLogger();

    String getName();

    String getDescription();
}
