package io.github.mosaicmc.mosaicapi.utils;

@SuppressWarnings("unused")
public sealed interface Option<T> {
    record Ok<T>(T value) implements Option<T> {

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Ok(Object val))) {
                return false;
            }
            return value.equals(val);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return "Ok{" +
                    "value=" + value +
                    '}';
        }
    }

    final class None<T> implements Option<T> {
        private static final None<?> INSTANCE = new None<>();

        private None() {
        }

        @SuppressWarnings("unchecked")
        public static <T> None<T> get() {
            return (None<T>) INSTANCE;
        }

        @Override
        public int hashCode() {
            return Integer.MIN_VALUE;
        }

        @Override
        public String toString() {
            return "None{}";
        }
    }

}
