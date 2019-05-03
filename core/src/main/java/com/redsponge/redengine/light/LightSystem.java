package com.redsponge.redengine.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.Assets;
import com.redsponge.redengine.assets.IAssetRequirer;

/**
 * A system that manages light. creates a light-map based on contained {@link Light} array.
 */
public class LightSystem implements Disposable, IAssetRequirer {

    /**
     * The frame buffer the light map is drawn onto
     */
    private FrameBuffer lightMap;

    private FrameBuffer shadeMap;

    /**
     * The array containing the lights
     */
    private DelayedRemovalArray<Light> lights;
    private DelayedRemovalArray<LightBlocker> blockers;

    private boolean hasShade;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private Assets assets;

    /**
     * The background of the background color of the light-map.
     */
    private Color ambianceColor;
    private FitViewport viewport;

    /**
     * All textures will be loaded here, since the lights are in an array
     */
    @Asset(path = "light/point_light.png")
    private Texture pointLight;


    /**
     * @param batch The batch to draw with
     * @param shapeRenderer The shape renderer to draw with
     * @param assets The assets holder
*      @param hasShade Should the light system have a shade map
     * @param viewport The viewport to draw for
     */
    public LightSystem(SpriteBatch batch, ShapeRenderer shapeRenderer, Assets assets, boolean hasShade, FitViewport viewport) {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        this.assets = assets;
        this.viewport = viewport;
        this.lightMap = new FrameBuffer(Format.RGBA8888, (int) viewport.getWorldWidth(), (int) viewport.getWorldHeight(), true);
        this.lights = new DelayedRemovalArray<>();
        this.blockers = new DelayedRemovalArray<>();
        this.ambianceColor = new Color(0, 0, 0, 1);
        this.hasShade = hasShade;

        if(hasShade) {
            shadeMap = new FrameBuffer(Format.RGBA8888, (int) viewport.getWorldWidth(), (int) viewport.getWorldHeight(), true);
        }
    }

    public void update(float delta) {
        for (Light light : lights) {
            light.update(delta);
        }
    }

    /**
     * Draws the light-map on the frame buffer, and retrieves it
     */
    public void prepare() {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        if(hasShade) {
            drawShadeMap();
        }

        lightMap.begin();
        Gdx.gl.glClearColor(ambianceColor.r, ambianceColor.g, ambianceColor.b, ambianceColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (Light light : lights) {
            light.render(batch);
        }

        if(hasShade) {
            Texture shade = shadeMap.getColorBufferTexture();

            batch.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_COLOR);
            batch.draw(shade, 0, 0);
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        }

        batch.end();

        lightMap.end();
    }

    private void drawShadeMap() {
        shadeMap.begin();
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        shapeRenderer.setColor(ambianceColor);

        for (LightBlocker blocker : blockers) {
            blocker.render(shapeRenderer);
        }

        shapeRenderer.end();

        shadeMap.end();
    }

    public void addLight(Light light) {
        light.load(assets);
        lights.add(light);
    }

    public void removeLight(Light light) {
        lights.removeValue(light, true);
    }

    public void addBlocker(LightBlocker lightBlocker) {
        blockers.add(lightBlocker);
    }

    public void removeBlocker(LightBlocker lightBlocker) {
        blockers.add(lightBlocker);
    }

    public Texture getLightMap() {
        return lightMap.getColorBufferTexture();
    }

    public void setViewport(FitViewport viewport) {
        this.viewport = viewport;
    }

    public FitViewport getViewport() {
        return viewport;
    }

    public void setAmbianceColor(Color ambianceColor) {
        this.ambianceColor = ambianceColor;
    }

    public Color getAmbianceColor() {
        return ambianceColor;
    }

    @Override
    public void dispose() {
        lightMap.dispose();
        if(hasShade) {
            shadeMap.dispose();
        }
    }
}
