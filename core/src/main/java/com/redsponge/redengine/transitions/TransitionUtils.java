package com.redsponge.redengine.transitions;

import com.badlogic.gdx.math.Interpolation;

public class TransitionUtils {

    public static float getProgress(float secondsSince, Interpolation from, Interpolation to, float length) {
        boolean before = secondsSince < length / 2;
        float raw = before ? secondsSince / (length / 2) : 1 - (secondsSince - (length / 2)) / (length / 2);
        if(before) {
            return from.apply(raw);
        } else {
            return to.apply(raw);
        }
    }

}
