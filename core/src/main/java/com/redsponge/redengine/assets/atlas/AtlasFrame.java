package com.redsponge.redengine.assets.atlas;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AtlasFrame {

    String frameName();
    String atlas();
    int index() default -1;

}
