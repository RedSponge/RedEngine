package com.redsponge.redengine.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

/**
 * An example to test the {@link LightSystem}
 */
public class LightTestScreen extends AbstractScreen implements InputProcessor {

    private LightSystem lightSystem;
    private FitViewport viewport;

    private Vector2 pos;

    @Asset(path = "world_tiles.png")
    private Texture testBackground;

    private PointLight light;

    public LightTestScreen(GameAccessor ga) {
        super(ga);
        viewport = new FitViewport(500, 500);
        lightSystem = new LightSystem(batch, shapeRenderer, assets, true, viewport);
        lightSystem.setAmbianceColor(new Color(0.1f, 0.1f, 0.57f, 1f));
    }

    @Override
    public void show() {
        pos = new Vector2();

        Gdx.input.setInputProcessor(this);
        lightSystem.addLight((light = new FlickeringPointLight(200, 200, 100, 5, 5)));
        lightSystem.addBlocker(new RectangularLightBlocker(100, 100, 20, 20));
    }

    @Override
    public void tick(float delta) {
        lightSystem.update(delta);
        light.getPosition().set(pos);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        lightSystem.prepare();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(testBackground, 0, 0, 500, 500);
        batch.end();


        // Set correct blend function
        batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);

        batch.begin();
        batch.draw(lightSystem.getLightMap(), 0, 0);
        batch.end();

        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void dispose() {
        lightSystem.dispose();
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
        light.setRadius(light.getRadius() + amount * -30);
        return false;
    }
}
