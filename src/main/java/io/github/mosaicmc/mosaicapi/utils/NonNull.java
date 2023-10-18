package io.github.mosaicmc.mosaicapi.utils;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.FIELD, ElementType.METHOD, ElementType.MODULE})
public @interface NonNull {
}
