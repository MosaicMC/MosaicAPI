package io.github.mosaicmc.mosaicapi.util;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Represents a bidirectional iterable collection of pairs.
 *
 * @param <T> The type of the first element in the pair.
 * @param <U> The type of the second element in the pair.
 */
public interface BiIterable<T, U> extends Iterable<Pair<T, U>> {

    /**
     * Creates a bidirectional iterable from a map.
     *
     * @param map The map to create the bidirectional iterable from.
     * @param <T> The type of keys in the map.
     * @param <U> The type of values in the map.
     * @return A {@link BiIterable} containing pairs derived from the map's entries.
     */
    static <T, U> BiIterable<T, U> of(Map<T, U> map) {
        return new BiIterable<>() {
            @NotNull
            @Override
            public Iterator<Pair<T, U>> iterator() {
                return map.entrySet().stream().map(entry -> new Pair<>(entry.getKey(), entry.getValue())).iterator();
            }

            @Override
            public void forEach(BiConsumer<T, U> consumer) {
                map.forEach(consumer);
            }
        };
    }

    static <T, U> BiIterable<T, U> of(List<Pair<T,U>> list) {
        return new BiIterable<>() {
            @NotNull
            @Override
            public Iterator<Pair<T, U>> iterator() {
                return list.iterator();
            }

            @Override
            public void forEach(BiConsumer<T, U> consumer) {
                list.forEach(pair -> {
                    consumer.accept(pair.a(), pair.b());
                });
            }
        };
    }

    /**
     * Performs an action for each pair in the bidirectional iterable.
     *
     * @param consumer The action to perform on each pair.
     */
    void forEach(BiConsumer<T, U> consumer);
}

