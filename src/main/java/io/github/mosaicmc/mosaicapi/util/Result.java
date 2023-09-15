package io.github.mosaicmc.mosaicapi.util;

public sealed interface Result<T, X> {
    record Ok<T, X>(T value) implements Result<T, X> {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Result.Ok<?, ?> ok && this.value.equals(ok.value);
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }

        @Override
        public String toString() {
            return "Ok(" + this.value + ")";
        }
    }

    record Err<T, X>(X value) implements Result<T, X> {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Result.Err<?, ?> err && this.value.equals(err.value);
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }

        @Override
        public String toString() {
            return "Err(" + this.value + ")";
        }
    }
}
