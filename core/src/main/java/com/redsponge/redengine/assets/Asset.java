package com.redsponge.redengine.assets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field as an asset which should be given a value and disposed on finish of a screen.
 * Used by {@link Assets} to verify any asset fields, load them and fill them up with values.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Asset {

    /**
     * The path of the asset, this is what the {@link Assets} will load when it reaches a field with the annotation.
     *
     * @return The value to the file to load
     */
    String value();

    /**
     * Should the asset be disposed when {@link Assets#unload()} is called.
     *
     * If there is at least one field requiring the same
     * asset which does not wait it to shouldDispose the field shall remain loaded and it becomes the programmer's responsibility to shouldDispose
     * it when needed.
     *
     * @return Should this asset be disposed.
     */
    boolean shouldDispose() default true;

}
