package io.github.mosaicmc.mosaicapi.utils;

import java.util.Objects;

@SuppressWarnings("unused")
public record Type<T>(String name) {
    public static <T> Type<T> from(Class<T> clazz) {
        return new Type<>(clazz.getName());
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}
