package com.redsponge.redengine.transitions;

import com.badlogic.gdx.math.Interpolation;

public class TransitionTemplate {

    public final Transition transition;
    public final float length;
    public final Interpolation interFrom;
    public final Interpolation interTo;

    public TransitionTemplate(Transition transition, float length, Interpolation interFrom, Interpolation interTo) {
        this.transition = transition;
        this.length = length;
        this.interFrom = interFrom;
        this.interTo = interTo;
    }
}
