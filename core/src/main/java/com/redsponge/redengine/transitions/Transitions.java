package com.redsponge.redengine.transitions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;

/**
 * A bunch of premade transitions free for use.
 */
public class Transitions {

    public static Transition sineSlide(float length, SpriteBatch batch, ShapeRenderer shapeRenderer) {
        return new TextureTransition(Interpolation.sineIn, Interpolation.sineIn, batch, shapeRenderer, length, TransitionTextures.getInstance().slide);
    }

    public static Transition linearDissolve(float length, SpriteBatch batch, ShapeRenderer shapeRenderer) {
        return new TextureTransition(Interpolation.linear, Interpolation.linear, batch, shapeRenderer, length, TransitionTextures.getInstance().dissolve);
    }

    public static Transition linearFade(float length, SpriteBatch batch, ShapeRenderer shapeRenderer) {
        return new FadeTransition(Interpolation.linear, Interpolation.linear, batch, shapeRenderer, length);
    }

    public static Transition sineCircle(float length, SpriteBatch batch, ShapeRenderer shapeRenderer) {
        return new TextureTransition(Interpolation.sineIn, Interpolation.sineIn, batch, shapeRenderer, length, TransitionTextures.getInstance().circleIn, false);
    }

    public static Transition sineRadial(float length, SpriteBatch batch, ShapeRenderer shapeRenderer) {
        return new TextureTransition(Interpolation.sineIn, Interpolation.sineIn, batch, shapeRenderer, length, TransitionTextures.getInstance().radial);
    }

}
