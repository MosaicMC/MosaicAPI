package io.github.mosaicmc.mosaicapi.api;

import io.github.mosaicmc.mosaicapi.utils.Type;

public interface Typed<T> {
    Type<T> getType();
}
