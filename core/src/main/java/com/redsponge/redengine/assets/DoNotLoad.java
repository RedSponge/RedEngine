package com.redsponge.redengine.assets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an {@link Asset} annotated field as Not Loaded. Or if an {@link IAssetRequirer} is being loaded, annotate
 * a nested {@link IAssetRequirer} to stop it from being loaded as well.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DoNotLoad {}
