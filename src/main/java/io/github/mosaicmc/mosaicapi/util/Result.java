package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

/**
 * Represents a result that can be either a success or a failure.
 *
 * @param <T> The type of the success value.
 * @param <X> The type of the error value.
 */
public sealed interface Result<T, X> {
    /**
     * Represents a successful result.
     *
     * @param <T> The type of the success value.
     * @param <X> The type of the error value.
     */
    record Success<T, X>(T ok) implements Result<T, X> {
        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof final Success<?, ?> success)) return false;
            return Objects.equals(ok, success.ok);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ok);
        }

        @Override
        public String toString() {
            return "Success{" +
                    "ok=" + ok +
                    '}';
        }
    }

    /**
     * Represents a failed result.
     *
     * @param <T> The type of the success value.
     * @param <X> The type of the error value.
     */
    record Failure<T, X>(X err) implements Result<T, X> {
        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof final Failure<?, ?> failure)) return false;
            return Objects.equals(err, failure.err);
        }

        @Override
        public int hashCode() {
            return Objects.hash(err);
        }

        @Override
        public String toString() {
            return "Failure{" +
                    "err=" + err +
                    '}';
        }
    }
}
