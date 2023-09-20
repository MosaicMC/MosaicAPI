package io.github.mosaicmc.mosaicapi.api.util;

public sealed interface Option<T> {
    record Some<T>(T value) implements Option<T> {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Some<?> some && this.value.equals(some.value);
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }

        @Override
        public String toString() {
            return "Some(" + this.value + ")";
        }
    }

    record None<T>() implements Option<T> {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof None;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public String toString() {
            return "None";
        }
    }
}
