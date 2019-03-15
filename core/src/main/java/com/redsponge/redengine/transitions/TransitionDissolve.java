package com.redsponge.redengine.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.utils.Logger;

public class TransitionDissolve implements Transition {

    private ShaderProgram shader;
    private SpriteBatch batch;
    private ScalingViewport viewport;
    private Texture noise;

    public TransitionDissolve(SpriteBatch batch) {
        this.batch = batch;
        this.viewport = new ScalingViewport(Scaling.fill, 138, 81);
        this.shader = new ShaderProgram(Gdx.files.internal("shaders/vert_pt.vert"), Gdx.files.internal("shaders/frag_dissolve.frag"));
        this.noise = new Texture("noise.png");
    }

    @Override
    public void render(float secondsSinceStart, Interpolation interFrom, Interpolation interTo, float length, ShapeRenderer shapeRenderer) {
        float progress = TransitionUtils.getProgress(secondsSinceStart, interFrom, interTo, length);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.setShader(this.shader);
        batch.begin();

        shader.setUniformf("dissolve", progress);

        batch.draw(noise, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.setShader(null);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        shader.dispose();
        noise.dispose();
    }
}
