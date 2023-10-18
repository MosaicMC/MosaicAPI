package io.github.mosaicmc.mosaicapi.utils;

public final class MutOnce<T> {
    private final boolean exception;
    private T value;
    private boolean mutated;

    public MutOnce(T value, boolean exception) {
        this.value = value;
        this.exception = exception;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        if (mutated && exception) {
            throw new IllegalStateException("Value can be only mutated once");
        } else if (mutated) {
            return;
        }
        this.value = value;
        mutated = true;
    }
}
