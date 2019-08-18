package com.redsponge.redengine.lighting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.redengine.render.util.SpriteBatchState;

public class LightMap implements Disposable {

    private static final Color DEFAULT_AMBIANCE_COLOR = new Color(0, 0, 0, 0);
    private static final SpriteBatchState tmpState = new SpriteBatchState();

    private FrameBuffer map;
    private DelayedRemovalArray<Light> lights;
    private Color ambianceColor;
    private LightType lightType;
    private TextureRegion tr;

    public LightMap(LightType lightType, int width, int height) {
        map = new FrameBuffer(Format.RGBA8888, width, height, false);
        tr = new TextureRegion(map.getColorBufferTexture());
        tr.flip(false, true);

        lights = new DelayedRemovalArray<>();
        ambianceColor = DEFAULT_AMBIANCE_COLOR.cpy();
        this.lightType = lightType;
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public void removeLight(Light light) {
        lights.removeValue(light, true);
    }

    public void prepareMap(LightSystem ls, SpriteBatch batch) {
        tmpState.extractState(batch);

        map.begin();
        Gdx.gl.glClearColor(ambianceColor.r, ambianceColor.g, ambianceColor.b, ambianceColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (Light light : lights) {
            light.render(ls, batch);
        }
        batch.end();

        map.end();

        tmpState.applyState(batch);
    }

    public void renderMap(LightSystem ls, SpriteBatch batch) {
        tmpState.extractState(batch);

        ls.getDrawingViewport().apply();
        batch.setProjectionMatrix(ls.getDrawingViewport().getCamera().combined);

        batch.setBlendFunction(lightType.getSrcBlend(), lightType.getDstBlend());
        batch.begin();
        batch.draw(tr, 0, 0);
        batch.end();

        tmpState.applyState(batch);
    }

    public void setAmbianceColor(Color ambianceColor) {
        this.ambianceColor.set(ambianceColor);
    }

    @Override
    public void dispose() {
        map.dispose();
        lights.clear();
    }
}
