package com.redsponge.redengine.assets.atlas;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AtlasAnimation {

    String animationName();
    String atlas();
    int length();
    int startsWith() default 1;
    PlayMode playMode() default PlayMode.LOOP;
    float frameDuration() default 0.1f;
}
