package com.redsponge.redengine.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private Light light;

    private int pixelWidth = 320, pixelHeight = 240;

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
        light = new PointLight(100, 100, 100);
        lightSystem.addLight(light);

        Gdx.input.setInputProcessor(this);
        lightmapRegion = new TextureRegion();
    }

    @Override
    public void tick(float delta) {
        if(Gdx.input.isKeyPressed(Keys.SPACE)) {
            viewport.getCamera().position.x++;
        } else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            light.getPosition().x++;
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        lightSystem.render();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(myTexture, 0, 0, 500, 500);
        batch.end();


//        lightMapViewport.apply();
//        batch.setProjectionMatrix(lightMapViewport.getCamera().combined);

        lightmapRegion.setRegion(lightSystem.getLightMap());
        lightmapRegion.flip(false, true);

        batch.begin();
        batch.draw(lightmapRegion, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
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
    public boolean mouseMoved(int screenX, int screenY) {
//        Vector2 p = viewport.unproject(new Vector2(screenX, screenY));
//        light.getPosition().set(p);
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
