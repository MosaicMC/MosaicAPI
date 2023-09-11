package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.ImmutableBiMap;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.ApiStatus;

public final class LoaderImpl implements Loader {
    @ApiStatus.Internal
    LoaderImpl() {}

    @Override
    public ImmutableBiMap<String, PluginContainer> getPlugins() throws NoSuchElementException {
        throw new NoSuchElementException("plugins aren't yet loaded");
    }
}
