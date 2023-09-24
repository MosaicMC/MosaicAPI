package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

public sealed interface Option {
    static <T> Option.None<T> None() {
        return new None<>();
    }

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
