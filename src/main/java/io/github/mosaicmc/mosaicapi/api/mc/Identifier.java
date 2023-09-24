package io.github.mosaicmc.mosaicapi.api.mc;

import io.github.mosaicmc.mosaicapi.impl.mc.MosaicIdentifier;
import io.github.mosaicmc.mosaicapi.util.Result;

import java.util.List;

public interface Identifier {
    static Identifier of(String id) {
        return MosaicIdentifier.of(id);
    }

    static Identifier of(String namespace, List<String> path) {
        return MosaicIdentifier.of(namespace, path);
    }

    static Result<Identifier, String> tryParse(String id) {
        return MosaicIdentifier.tryParse(id);
    }

    static boolean isValid(String id) {
        return MosaicIdentifier.isValid(id);
    }

    String getNamespace();

    List<String> getPaths();

    String getId();
}
