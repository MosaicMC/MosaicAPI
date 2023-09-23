package io.github.mosaicmc.mosaicapi.util;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

public interface BiIterable<T,U> extends Iterable<Pair<T,U>> {
    static <T,U> BiIterable<T,U> of(Map<T,U> map) {
        return new BiIterable<>() {
            @NotNull
            @Override
            public Iterator<Pair<T,U>> iterator() {
                return map.entrySet().stream().map(entry -> new Pair<>(entry.getKey(), entry.getValue())).iterator();
            }

            @Override
            public void forEach(BiConsumer<T,U> consumer) {
                map.forEach(consumer);
            }
        };
    }
    void forEach(BiConsumer<T,U> consumer);
}
