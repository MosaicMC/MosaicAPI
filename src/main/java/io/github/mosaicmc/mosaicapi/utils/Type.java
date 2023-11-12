package io.github.mosaicmc.mosaicapi.utils;

@SuppressWarnings("unused")
public record Type<T>(String name) {
    public static <T> Type<T> from(Class<T> clazz) {
        return new Type<>(clazz.getName());
    }
}
