package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

/**
 * A simple record representing a pair of values.
 *
 * @param <A> The type of the first value.
 * @param <B> The type of the second value.
 */
public record Pair<A, B>(A a, B b) {
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
