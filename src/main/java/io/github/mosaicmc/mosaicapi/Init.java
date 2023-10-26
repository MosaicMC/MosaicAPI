package io.github.mosaicmc.mosaicapi;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public final class Init<T> {
    private final AtomicReference<@Nullable T> atomicValue = new AtomicReference<>();

    public boolean setter(Supplier<T> value) {
        if (this.atomicValue.get() != null) return false;
        this.atomicValue.set(value.get());
        return true;
    }

    public T get() {
        return Objects.requireNonNull(atomicValue.get());
    }
}
