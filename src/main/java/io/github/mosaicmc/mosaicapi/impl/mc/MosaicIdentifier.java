package io.github.mosaicmc.mosaicapi.impl.mc;

import io.github.mosaicmc.mosaicapi.api.mc.Identifier;
import io.github.mosaicmc.mosaicapi.util.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MosaicIdentifier implements Identifier {
    private final String namespace;
    private final List<String> paths;
    private final String id;

    private MosaicIdentifier(String namespace, List<String> paths) {
        this.namespace = namespace;
        this.paths = paths;
        this.id = namespace + ":" + String.join("/", paths);
    }

    public static Identifier of(String id) {
        String[] parts = id.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid identifier format: " + id);
        }

        String namespace = parts[0];
        String[] pathParts = parts[1].split("/");
        List<String> pathList = new ArrayList<>();

        for (String pathPart : pathParts) {
            if (isNotValidPathComponent(pathPart)) {
                throw new IllegalArgumentException("Invalid path component: " + pathPart);
            }
            pathList.add(pathPart);
        }

        return new MosaicIdentifier(namespace, pathList);
    }

    public static Identifier of(String namespace, List<String> path) {
        for (String pathPart : path) {
            if (isNotValidPathComponent(pathPart)) {
                throw new IllegalArgumentException("Invalid path component: " + pathPart);
            }
        }
        return new MosaicIdentifier(namespace, path);
    }

    public static Result<Identifier, String> tryParse(String id) {
        try {
            Identifier parsedIdentifier = of(id);
            return new Result.Success<>(parsedIdentifier);
        } catch (IllegalArgumentException e) {
            return new Result.Failure<>(e.getMessage());
        }
    }

    public static boolean isValid(String id) {
        try {
            of(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isNotValidPathComponent(String pathComponent) {
        return !pathComponent.matches("^[0-9a-z_-]+$");
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
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MosaicIdentifier{" +
                "namespace='" + namespace + '\'' +
                ", paths=" + paths +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final MosaicIdentifier that)) return false;
        return Objects.equals(namespace, that.namespace) && Objects.equals(paths, that.paths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, paths);
    }
}
