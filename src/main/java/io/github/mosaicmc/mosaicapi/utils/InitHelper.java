package io.github.mosaicmc.mosaicapi.utils;

import org.jetbrains.annotations.Nullable;
import oshi.annotation.concurrent.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

@ThreadSafe
public final class InitHelper<T> {
    private final AtomicReference<@Nullable T> atomicValue = new AtomicReference<>();

    public T initialize(Supplier<T> supplier) {
        requireNonNull(supplier);
        if (this.atomicValue.get() != null) return get();
        final var value = requireNonNull(supplier.get());
        this.atomicValue.set(value);
        return value;
    }

    public void initialize(T initValue) {
        requireNonNull(initValue);
        if (this.atomicValue.get() != null) return;
        this.atomicValue.set(initValue);
    }

    public boolean isInitialized() {
        return this.atomicValue.get() != null;
    }

    public T get() {
        return requireNonNull(atomicValue.get());
    }
}