package com.redsponge.redengine.transitions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class TransitionLine implements Transition {

    private ScalingViewport viewport;

    public TransitionLine() {
        viewport = new ScalingViewport(Scaling.fill, 1, 1);
    }

    @Override
    public void render(float secondsSinceStart, Interpolation interFrom, Interpolation interTo, float length, ShapeRenderer shapeRenderer) {
        final float progress = TransitionUtils.getProgress(secondsSinceStart, interFrom, interTo, length);
        viewport.apply();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);

        if(secondsSinceStart / length <= 0.5f) {
            shapeRenderer.rect(0, 0, progress, 1);
        } else {
            shapeRenderer.rect(1-progress, 0, 1, 1);
        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {

    }
}
