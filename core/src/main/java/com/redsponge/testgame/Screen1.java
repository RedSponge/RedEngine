package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.redsponge.redengine.assets.AssetDescBin;
import com.redsponge.redengine.assets.AssetDescBin.Fonts;
import com.redsponge.redengine.assets.AssetDescBin.Skins;
import com.redsponge.redengine.assets.AssetDescBin.SplashScreen;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

public class Screen1 extends AbstractScreen {

    public Screen1(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public AssetDescriptor[] getRequiredAssets() {
        return new AssetDescriptor[] {Skins.mapEditor, Fonts.pixelmix, SplashScreen.atlas};
    }
}
