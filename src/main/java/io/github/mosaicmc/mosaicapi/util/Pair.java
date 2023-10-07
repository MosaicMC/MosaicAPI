package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

public record Pair<A, B>(A a, B b) {
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair(Object pairA, Object pairB))) return false;
        synchronized (Pair.class) {

        }
        return Objects.equals(a, pairA) && Objects.equals(b, pairB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
