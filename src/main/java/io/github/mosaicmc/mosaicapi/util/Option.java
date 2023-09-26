package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

/**
 * Represents an option that may or may not contain a value.
 */
public sealed interface Option {

    /**
     * Creates an empty option.
     *
     * @param <T> The type parameter of the option.
     * @return An empty {@link None} option.
     */
    static <T> Option.None<T> None() {
        return new None<>();
    }

    /**
     * Represents an option that contains a non-null value.
     *
     * @param <T> The type of the value.
     */
    record Some<T>(T value) implements Option {
        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Some<?>(var someValue))) return false;
            return Objects.equals(value, someValue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Some{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * Represents an empty option.
     *
     * @param <T> The type parameter of the option.
     */
    final class None<T> implements Option {
        private None() {
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            return o instanceof None<?>;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public String toString() {
            return "None{}";
        }
    }
}
