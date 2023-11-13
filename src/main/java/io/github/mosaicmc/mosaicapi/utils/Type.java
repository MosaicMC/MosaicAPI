package io.github.mosaicmc.mosaicapi.utils;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class Type<T> {
    private final String name;

    public static <T> Type<T> from(Class<T> clazz) {
        return new Type<>(clazz.getName());
    }
}
