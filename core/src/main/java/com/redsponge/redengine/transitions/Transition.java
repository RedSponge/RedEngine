package com.redsponge.redengine.transitions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Describes a transition between two screens. a transition is a mask that is drawn on
 */
public abstract class Transition implements Disposable {

    protected Interpolation interFrom;
    protected Interpolation interTo;

    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;

    protected float length;

    public Transition(Interpolation interFrom, Interpolation interTo, SpriteBatch batch, ShapeRenderer shapeRenderer, float length) {
        this.interFrom = interFrom;
        this.interTo = interTo;
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        this.length = length;
    }

    /**
     * Draw the mask here, this will be called for every frame the transition is run
     * @param time How much time has passed since the transition's start, in seconds
     */
    public abstract void render(float time);

    /**
     * used to update {@link Viewport}s
     */
    public abstract void resize(int width, int height);

    /**
     * Maps the time to a 0-1 progress bar
     * @param time
     * @return
     */
    protected float getProgress(float time, boolean reverseAfterHalf) {
        boolean before = time < length / 2;
        float raw;

        if(reverseAfterHalf) {
            raw = before ? time / (length / 2) : 1 - (time - (length / 2)) / (length / 2);
        } else {
            raw = (time % (length / 2)) / (length / 2);
        }

        if(before) {
            return interFrom.apply(raw);
        } else {
            return interTo.apply(raw);
        }
    }

    public boolean isDone(float timeSince) {
        return timeSince > length;
    }

    public float getLength() {
        return length;
    }

    public Interpolation getInterFrom() {
        return interFrom;
    }

    public Interpolation getInterTo() {
        return interTo;
    }
}
