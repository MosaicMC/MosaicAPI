package io.github.mosaicmc.mosaicapi.utils;

public class AlreadyLoadedException extends RuntimeException {
    public AlreadyLoadedException(String message) {
        super(message);
    }
}
