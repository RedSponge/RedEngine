package com.redsponge.redengine.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

public class LightTestScreen extends AbstractScreen implements InputProcessor {

    private LightRenderer lightRenderer;
    private FitViewport viewport;

    private Vector2 pos;

    @Asset(path = "world_tiles.png")
    private Texture testBackground;
    private float time;

    public LightTestScreen(GameAccessor ga) {
        super(ga);
        viewport = new FitViewport(500, 500);
        lightRenderer = new LightRenderer(500, 500, batch, assets, viewport);
    }

    @Override
    public void show() {
        pos = new Vector2();

        Gdx.input.setInputProcessor(this);
        time = 0;
        lightRenderer.addLight(new FlickeringPointLight(200, 200, 100, 50, 50));
    }

    @Override
    public void tick(float delta) {
        time += delta;
        lightRenderer.update(delta);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        lightRenderer.render();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(testBackground, 0, 0, 500, 500);
        batch.end();


        batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);

        batch.begin();
        batch.draw(lightRenderer.getLightMap(), 0, 0);
        batch.end();

        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 p = viewport.unproject(new Vector2(screenX, screenY));
        p.y = viewport.getWorldHeight() - p.y;
        pos.set(p);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 p = viewport.unproject(new Vector2(screenX, screenY));
        p.y = viewport.getWorldHeight() - p.y;
        pos.set(p);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 p = viewport.unproject(new Vector2(screenX, screenY));
        p.y = viewport.getWorldHeight() - p.y;
        pos.set(p);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector2 p = viewport.unproject(new Vector2(screenX, screenY));
        p.y = viewport.getWorldHeight() - p.y;
        pos.set(p);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
