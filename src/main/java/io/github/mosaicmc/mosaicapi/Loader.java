package io.github.mosaicmc.mosaicapi;

import com.google.common.collect.ImmutableBiMap;
import java.util.NoSuchElementException;

public sealed interface Loader permits LoaderImpl {
    Loader INSTANCE = new LoaderImpl();

    ImmutableBiMap<String, PluginContainer> getPlugins() throws NoSuchElementException;
}
