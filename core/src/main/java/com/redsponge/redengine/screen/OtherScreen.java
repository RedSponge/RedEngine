package com.redsponge.redengine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.redsponge.redengine.utils.GameAccessor;

public class OtherScreen extends AbstractScreen {

    public OtherScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
