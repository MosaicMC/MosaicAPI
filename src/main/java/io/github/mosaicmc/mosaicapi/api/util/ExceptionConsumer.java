package io.github.mosaicmc.mosaicapi.api.util;

public interface ExceptionConsumer<T> {
    void accept(T exception) throws Throwable;
}
