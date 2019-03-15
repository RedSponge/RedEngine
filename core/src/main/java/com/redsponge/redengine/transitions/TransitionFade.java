package com.redsponge.redengine.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.utils.Logger;

public class TransitionFade implements Transition {

    private Viewport viewport;

    public TransitionFade() {
        viewport = new ScalingViewport(Scaling.fill, 1, 1);
    }

    @Override
    public void render(float secondsSinceStart, Interpolation interFrom, Interpolation interTo, float length, ShapeRenderer shapeRenderer) {
        float progress = TransitionUtils.getProgress(secondsSinceStart, interFrom, interTo, length);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, progress);
        shapeRenderer.rect(0,0, 1, 1);
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {

    }
}
