package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

public record Pair<A,B>(A a, B b) {
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final Pair<?, ?> pair)) return false;
        return Objects.equals(a, pair.a) && Objects.equals(b, pair.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}