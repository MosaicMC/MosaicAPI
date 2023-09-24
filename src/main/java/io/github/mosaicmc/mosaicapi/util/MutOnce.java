package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

public final class MutOnce<T> {
    private T value;
    private boolean mutated = false;

    public MutOnce(T value) {
        this.value = value;
    }

    synchronized public void mutate(T value) {
        if (mutated) {
            throw new IllegalStateException("Value already mutated");
        }
        this.value = value;
        mutated = true;
    }

    public T get() {
        return value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MutOnce<?> that)) {
            return false;
        }
        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, mutated);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
