package io.github.mosaicmc.mosaicapi.utils;

import org.jetbrains.annotations.Nullable;
import oshi.annotation.concurrent.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.requireNonNull;

@ThreadSafe
public final class InitHelper<T> {
    private final AtomicReference<@Nullable T> atomicValue = new AtomicReference<>();

    public T initialize(T initValue) {
        requireNonNull(initValue);
        if (this.atomicValue.get() != null) return initValue;
        this.atomicValue.set(initValue);
        return initValue;
    }

    public boolean isInitialized() {
        return this.atomicValue.get() != null;
    }

    public T get() {
        return requireNonNull(atomicValue.get());
    }
}
