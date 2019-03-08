package com.redsponge.redengine.transitions;

import com.badlogic.gdx.math.Interpolation;

public class TransitionTemplates {

    public static final TransitionTemplate sineSlide = new TransitionTemplate(new TransitionLine(), 1f, Interpolation.sineIn, Interpolation.sineIn);
    public static final TransitionTemplate linearFade = new TransitionTemplate(new TransitionFade(), 1f, Interpolation.linear, Interpolation.linear);

}
