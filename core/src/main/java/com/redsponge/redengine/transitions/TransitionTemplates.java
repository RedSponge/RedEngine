package com.redsponge.redengine.transitions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TransitionTemplates {

    public static TransitionTemplate sineSlide(float length) {
        return new TransitionTemplate(new TransitionLine(), length, Interpolation.sineIn, Interpolation.sineIn);
    }

    public static TransitionTemplate linearFade(float length) {
        return new TransitionTemplate(new TransitionFade(), length, Interpolation.linear, Interpolation.linear);
    }

    public static TransitionTemplate linearDissolve(SpriteBatch batch, float length) {
        return new TransitionTemplate(new TransitionDissolve(batch), length, Interpolation.linear, Interpolation.linear);
    }

}
