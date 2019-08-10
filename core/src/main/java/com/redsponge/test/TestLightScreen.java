package com.redsponge.test;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.lighting.LightSystem;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

public class TestLightScreen extends AbstractScreen {

    private LightSystem ls;
    private FitViewport viewport;
    //TODO: Finish testing
    public TestLightScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        ls = new LightSystem()
    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public void render() {

    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return null;
    }
}
