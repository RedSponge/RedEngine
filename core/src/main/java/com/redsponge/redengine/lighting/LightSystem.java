package com.redsponge.redengine.lighting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.render.util.SpriteBatchState;

public class LightSystem {

    private DelayedRemovalArray<Light> lights;

    private FrameBuffer lightMap;
    private TextureRegion lightMapRegion;

    private FitViewport drawingViewport;

    private SpriteBatch batch;

    private Color ambianceColor;

    private SpriteBatchState tempState;

    public LightSystem(int width, int height, SpriteBatch batch) {
        this.batch = batch;
        drawingViewport = new FitViewport(width, height);
        lightMap = new FrameBuffer(Format.RGBA8888, width, height, false);

        lightMapRegion = new TextureRegion(lightMap.getColorBufferTexture(), width, height);
        lightMapRegion.flip(false, true);

        lights = new DelayedRemovalArray<Light>();

        ambianceColor = Color.BLACK;

        tempState = new SpriteBatchState();
    }

    public void prepareMap() {
        lightMap.begin();

        Gdx.gl.glClearColor(ambianceColor.r, ambianceColor.g, ambianceColor.b, ambianceColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (Light light : lights) {
            light.render(this, batch);
        }

        lightMap.end();
    }

    public void renderToScreen() {
        tempState.extractState(batch);

        drawingViewport.apply();
        batch.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_COLOR);
        batch.setProjectionMatrix(drawingViewport.getCamera().combined);

        batch.begin();
        batch.draw(lightMapRegion, 0, 0);
        batch.end();

        tempState.applyState(batch);
    }


    public Color getAmbianceColor() {
        return ambianceColor;
    }

    public void setAmbianceColor(Color ambianceColor) {
        this.ambianceColor = ambianceColor;
    }

    public void resize(int width, int height) {
        drawingViewport.update(width, height, true);
    }
}
