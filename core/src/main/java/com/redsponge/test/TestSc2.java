package com.redsponge.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

public class TestSc2 extends AbstractScreen {

    private float x;

    public TestSc2(GameAccessor ga) {
        super(ga);
    }

    private Viewport viewport;
    private FrameBuffer fb;

    @Override
    public void show() {
        fb = new FrameBuffer(Format.RGBA8888, 100, 100, true);
        viewport = new FitViewport(100, 100);
    }

    @Override
    public void tick(float delta) {
        x++;
    }

    @Override
    public void render() {
        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fb.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(10, 10, 20, 20);
        shapeRenderer.end();
        fb.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
//        batch.draw(fb.getColorBufferTexture(), 0, 0, 50, 50);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return null;
    }
}
