package com.redsponge.test.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.physics.PhysicsDebugRenderer;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.screen.systems.PhysicsSystem;
import com.redsponge.redengine.screen.systems.RenderSystem;
import com.redsponge.redengine.utils.GameAccessor;

public class PlatformerScreen extends AbstractScreen {

    private RenderSystem renderSystem;
    private PhysicsSystem physicsSystem;
    private PhysicsDebugRenderer pdr;

    public PlatformerScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        addEntity(new Background());
        addEntity(new Platform(0, 0, getScreenWidth(), 20));
        renderSystem = getEntitySystem(RenderSystem.class);
        physicsSystem = getEntitySystem(PhysicsSystem.class);
        renderSystem.setBackground(Color.WHITE);

        addEntity(new PlatformerPlayer());
        pdr = new PhysicsDebugRenderer();
    }

    @Override
    public void tick(float delta) {
        tickEntities(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateEngine(delta);
    }

    @Override
    public int getScreenWidth() {
        return 640;
    }

    @Override
    public int getScreenHeight() {
        return 360;
    }

    @Override
    public void render() {
        pdr.render(physicsSystem.getPhysicsWorld(), renderSystem.getCamera().combined);
    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return PlatformerAssets.class;
    }

    @Override
    public void reSize(int width, int height) {
        renderSystem.resize(width, height);
    }

    @Override
    public void disposeAssets() {
//        pdr.dispose();
    }
}
