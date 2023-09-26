package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

/**
 * A simple record representing a triplet of values.
 *
 * @param <A> The type of the first value.
 * @param <B> The type of the second value.
 * @param <C> The type of the third value.
 */
public record Triplet<A, B, C>(A a, B b, C c) {
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final Triplet<?, ?, ?> triplet)) return false;
        return Objects.equals(a, triplet.a) && Objects.equals(b, triplet.b) && Objects.equals(c, triplet.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
