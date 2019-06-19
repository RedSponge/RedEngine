package com.redsponge.redengine.transitions;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Describes a transition between two screens. a transition is a mask that is drawn on
 */
public interface Transition extends Disposable {

    /**
     * Draw the mask here, this will be called for every frame the transition is run
     * @param secondsSinceStart How much time has passed since the transition's start, in seconds
     * @param interFrom The interpolation of the first part of the transition
     * @param interTo The interpolation of the second part of the transition
     * @param length The length of the transition
     * @param shapeRenderer The {@link ShapeRenderer} to render the transition
     */
    void render(float secondsSinceStart, Interpolation interFrom, Interpolation interTo, float length, ShapeRenderer shapeRenderer);

    /**
     * used to update {@link Viewport}s
     */
    void resize(int width, int height);
}
