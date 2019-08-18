package com.redsponge.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.lighting.LightSystem;
import com.redsponge.redengine.lighting.LightTextures;
import com.redsponge.redengine.lighting.LightType;
import com.redsponge.redengine.lighting.PointLight;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;
import com.redsponge.redengine.utils.Logger;

public class TestLightScreen extends AbstractScreen implements InputProcessor {

    private LightSystem ls;
    private FitViewport viewport;

    private PointLight light;
    private PointLight innerLight;

    private static final int WIDTH = 320 * 2;
    private static final int HEIGHT = 180 * 2;

    private Texture sad;

    public TestLightScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        viewport = new FitViewport(WIDTH, HEIGHT);
        ls = new LightSystem(WIDTH, HEIGHT, batch);

        light = new PointLight(50, 50, 200, LightTextures.getInstance().starPointLight);
        light.getColor().set(1, 1, 1, 1);

        innerLight = new PointLight(50, 50, 100, LightTextures.getInstance().starPointLight);
        innerLight.getColor().set(0.5f, 0.5f, 0.5f, 1);

        ls.registerLightType(LightType.MULTIPLICATIVE);
        ls.setAmbianceColor(new Color(0.1f, 0.1f, 0.2f, 1.0f), LightType.MULTIPLICATIVE);
        ls.addLight(light, LightType.MULTIPLICATIVE);

        ls.registerLightType(LightType.ADDITIVE);
        ls.addLight(innerLight, LightType.ADDITIVE);

        sad = assets.get("sad", Texture.class);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void tick(float delta) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();


        viewport.unproject(light.pos.set(x, y));
        innerLight.pos.set(light.pos);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(sad, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.end();

        ls.prepareMap(LightType.MULTIPLICATIVE);
        ls.renderToScreen(LightType.MULTIPLICATIVE);

        ls.prepareMap(LightType.ADDITIVE);
        ls.renderToScreen(LightType.ADDITIVE);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        ls.resize(width, height);
    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return TestScreenLightAssets.class;
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
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        amount *= 10;
        Logger.log(this, amount);
        light.setRadius(light.getRadius() + amount);
        innerLight.setRadius(innerLight.getRadius() + amount);
        return false;
    }
}
