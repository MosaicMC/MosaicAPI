package io.github.mosaicmc.mosaicapi.api.mc;

import io.github.mosaicmc.mosaicapi.impl.mc.MosaicIdentifier;
import io.github.mosaicmc.mosaicapi.util.Result;

import java.util.List;

public interface Identifier {

    /**
     * Creates an identifier from a single string.
     *
     * @param id The identifier string.
     * @return An {@link Identifier} instance.
     */
    static Identifier of(String id) {
        return MosaicIdentifier.of(id);
    }

    /**
     * Creates an identifier from a namespace and a list of path components.
     *
     * @param namespace The namespace for the identifier.
     * @param path      The list of path components.
     * @return An {@link Identifier} instance.
     */
    static Identifier of(String namespace, List<String> path) {
        return MosaicIdentifier.of(namespace, path);
    }

    /**
     * Tries to parse an identifier from a string.
     *
     * @param id The identifier string.
     * @return A {@link Result} containing the parsed {@link Identifier} or an error message.
     */
    static Result<Identifier, String> tryParse(String id) {
        return MosaicIdentifier.tryParse(id);
    }

    /**
     * Checks if a given identifier string is valid.
     *
     * @param id The identifier string to validate.
     * @return {@code true} if the identifier is valid, {@code false} otherwise.
     */
    static boolean isValid(String id) {
        return MosaicIdentifier.isValid(id);
    }

    /**
     * Gets the namespace of the identifier.
     *
     * @return The namespace.
     */
    String getNamespace();

    /**
     * Gets the list of path components of the identifier.
     *
     * @return The list of path components.
     */
    List<String> getPaths();

    /**
     * Gets the full identifier string.
     *
     * @return The identifier string.
     */
    String getId();
}

