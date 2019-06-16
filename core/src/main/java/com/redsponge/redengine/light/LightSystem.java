package com.redsponge.redengine.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.Assets;
import com.redsponge.redengine.assets.IAssetRequirer;

public class LightSystem implements Disposable, IAssetRequirer {

    private FrameBuffer fbo;
    private SpriteBatch batch;
    private Assets assets;
    private DelayedRemovalArray<Light> lights;

    private Color ambianceColor;
    private FitViewport viewport;

    @Asset(path = "light/point_light.png")
    private Texture pointLight;

    public LightSystem(SpriteBatch batch, Assets assets, FitViewport viewport) {
        this.batch = batch;
        this.assets = assets;
        this.viewport = viewport;
        fbo = new FrameBuffer(Format.RGBA8888, (int) viewport.getWorldWidth(), (int) viewport.getWorldHeight(), true);
        lights = new DelayedRemovalArray<>();
        ambianceColor = new Color(0, 0, 0, 1);
    }

    public void update(float delta) {
        for (Light light : lights) {
            light.update(delta);
        }
    }

    public void render() {
        viewport.apply();
//        batch.setProjectionMatrix(viewport.getCamera().combined);
        fbo.begin();
        Gdx.gl.glClearColor(ambianceColor.r, ambianceColor.g, ambianceColor.b, ambianceColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (Light light : lights) {
            light.render(batch, viewport);
        }
        batch.end();

        fbo.end();
    }

    public void setAmbianceColor(Color ambianceColor) {
        this.ambianceColor = ambianceColor;
    }

    public void addLight(Light light) {
        light.load(assets);
        lights.add(light);
    }

    public void removeLight(Light light) {
        lights.removeValue(light, true);
    }

    public Texture getLightMap() {
        return fbo.getColorBufferTexture();
    }

    public void setViewport(FitViewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public void dispose() {
        fbo.dispose();
    }
}