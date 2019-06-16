package com.redsponge.redengine.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

/**
 * An example to test the {@link LightSystem}
 */
public class LightTestScreen extends AbstractScreen implements InputProcessor {

    private FitViewport viewport;
    private FitViewport lightMapViewport;

    @Asset(path = "world_tiles.png")
    private Texture myTexture;
    private Vector2 pos;

    private LightSystem lightSystem;
    private PointLight light;

    private int pixelWidth = 360, pixelHeight = 240;

    @Asset(path = "light/point_light.png")
    private Texture lightTexture;

    private TextureRegion lightmapRegion;

    public LightTestScreen(GameAccessor ga) {
        super(ga);
        viewport = new FitViewport(pixelWidth, pixelHeight);
        lightMapViewport = new FitViewport(pixelWidth, pixelHeight);
    }

    @Override
    public void show() {
        lightSystem = new LightSystem(batch, assets, viewport);
        lightSystem.setAmbianceColor(new Color(0, 0, 0, 1));

        Gdx.input.setInputProcessor(this);
        lightmapRegion = new TextureRegion();

        viewport.apply(true);
    }

    @Override
    public void tick(float delta) {
        lightSystem.update(delta);
        if(Gdx.input.isKeyPressed(Keys.SPACE)) {
            viewport.getCamera().position.x++;
        }
    }

    public void newLight(float x, float y) {

        float r = MathUtils.random(0.8f, 1);
        float g = MathUtils.random(0.8f, 1);
        float b = MathUtils.random(0.8f, 1);

        float rad = MathUtils.random(80, 120);
        PointLight l = new FlickeringPointLight(x, y, rad, 1, 20);
        l.setColor(new Color(r, g, b, 1));

        lightSystem.addLight(l);
    }

    @Override
    public void render() {
        lightSystem.render();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        batch.draw(myTexture, 0, 0, pixelWidth * 20, pixelHeight * 20);
        batch.end();


        lightMapViewport.apply();
        batch.setProjectionMatrix(lightMapViewport.getCamera().combined);

        lightmapRegion.setRegion(lightSystem.getLightMap());
        lightmapRegion.flip(false, true);

        lightMapViewport.apply();
        batch.setProjectionMatrix(lightMapViewport.getCamera().combined);

        batch.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_COLOR);

        batch.begin();
        batch.draw(lightmapRegion, 0, 0);
        batch.end();

        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        lightMapViewport.update(width, height, true);
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
    public boolean mouseMoved(int screenX, int screenY) {
//        Vector2 p = viewport.unproject(new Vector2(screenX, screenY));
//        light.getPosition().set(p);
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 vec = new Vector2(screenX, screenY);
        viewport.unproject(vec);
        newLight(vec.x, vec.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
