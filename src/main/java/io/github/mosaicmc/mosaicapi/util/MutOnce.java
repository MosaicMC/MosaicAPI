package io.github.mosaicmc.mosaicapi.util;

import java.util.Objects;

/**
 * A class that allows a value to be mutated only once.
 *
 * @param <T> The type of the value.
 */
public final class MutOnce<T> {

    /**
     * The value to be mutated.
     */
    private T value;

    /**
     * Indicates whether the value has been mutated.
     */
    private boolean mutated = false;

    /**
     * Constructs a MutOnce instance with an initial value.
     *
     * @param value The initial value.
     */
    public MutOnce(T value) {
        this.value = value;
    }

    /**
     * Mutates the value if it has not been mutated before.
     *
     * @param value The new value to set.
     * @throws IllegalStateException if the value has already been mutated.
     */
    synchronized public void mutate(T value) {
        if (mutated) {
            throw new IllegalStateException("Value already mutated");
        }
        this.value = value;
        mutated = true;
    }

    /**
     * Gets the current value.
     *
     * @return The current value.
     */
    public T get() {
        return value;
    }

    /**
     * Compares this MutOnce instance to another object for equality.
     *
     * @param obj The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
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

    /**
     * Computes the hash code of this MutOnce instance.
     *
     * @return The computed hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, mutated);
    }

    /**
     * Returns a string representation of this MutOnce instance.
     *
     * @return A string representation of the value.
     */
    @Override
    public String toString() {
        return value.toString();
    }
}

