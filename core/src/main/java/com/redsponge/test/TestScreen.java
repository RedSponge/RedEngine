package com.redsponge.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.render.util.NoiseGenerator;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.transitions.Transitions;
import com.redsponge.redengine.utils.GameAccessor;

public class TestScreen extends AbstractScreen {

    private FitViewport viewport;
    private Texture noise;

    public TestScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        viewport = new FitViewport(1600, 900);

        addEntity(new Player(batch, shapeRenderer));
        addEntity(new Background(batch, shapeRenderer));
        noise = NoiseGenerator.createNoiseTexture(640, 360);
    }

    @Override
    public void tick(float delta) {
        tickEntities(delta);
        if(Gdx.input.isKeyPressed(Keys.SPACE) && !transitioning) {
            ga.transitionTo(new TestSc2(ga), Transitions.sineSlide(1, batch, shapeRenderer));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        renderEntities();
        batch.draw(noise, 0, 0);
        batch.end();
    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return TestSpecs.class;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
