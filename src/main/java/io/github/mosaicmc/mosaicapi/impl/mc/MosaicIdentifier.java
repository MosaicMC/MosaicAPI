package io.github.mosaicmc.mosaicapi.impl.mc;

import io.github.mosaicmc.mosaicapi.api.mc.Identifier;
import io.github.mosaicmc.mosaicapi.util.Result;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class MosaicIdentifier implements Identifier {
    private final String namespace;
    private final List<String> paths;

    public MosaicIdentifier(final String namespace, final List<String> paths) {
        this.namespace = namespace;
        this.paths = paths;
    }

    public static Identifier of(String id) {
        Result<Identifier, String> result = tryParse(id);
        return switch (result) {
            case Result.Success(final var success) -> success;
            case Result.Failure(final var error) -> throw new IllegalArgumentException(error);
        };
    }

    public static Identifier of(String namespace, List<String> path) {
        return new MosaicIdentifier(namespace, path);
    }

    public static Result<Identifier, String> tryParse(String id) {
        String[] parts = id.split("@");
        if (parts.length < 2) {
            return new Result.Failure<>("Invalid input format: " + id);
        }
        final var namespace = parts[0];
        if (!isValid(namespace)) {
            return new Result.Failure<>("Invalid input format: " + id);
        }
        String[] pathArray = parts[1].split("\\.");
        if (!isValidPaths(pathArray)) {
            return new Result.Failure<>("Invalid input format: " + id);
        }
        List<String> paths = List.of(pathArray);

        return new Result.Success<>(new MosaicIdentifier(namespace, paths));
    }

    public static boolean isValid(String id) {
        return tryParse(id) instanceof Result.Success;
    }

    private static boolean isValidPaths(String[] paths) {
        for (String path : paths) {
            if (!isValidInput(path)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidInput(String input) {
        return input.matches("^[a-z_]+$");
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public List<String> getPaths() {
        return paths;
    }

    @Override
    public int compareTo(@NotNull final Identifier o) {
        return 0;
    }
}
