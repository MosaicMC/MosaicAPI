package io.github.mosaicmc.mosaicapi.util;

public interface ExceptionConsumer<T> {
    void accept(T exception) throws Throwable;
}
