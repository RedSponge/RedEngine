package com.redsponge.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.physics.PhysicsDebugRenderer;
import com.redsponge.redengine.physics.PhysicsWorld;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

public class TestPScreen extends AbstractScreen {

    private PhysicsWorld pWorld;
    private PhysicsDebugRenderer pdr;

    private FitViewport viewport;

    public TestPScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        pdr = new PhysicsDebugRenderer();
        pWorld = new PhysicsWorld();
        pWorld.addSolid(new MovingSolid(pWorld));
        pWorld.addActor(new ActorGuy(pWorld));

        viewport = new FitViewport(500, 500);
    }

    @Override
    public void tick(float delta) {
        pWorld.update(delta);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        pdr.render(pWorld, viewport.getCamera().combined);
    }

    @Override
    public void reSize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return null;
    }
}
