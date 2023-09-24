package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

public sealed interface Result<T, X> {
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
