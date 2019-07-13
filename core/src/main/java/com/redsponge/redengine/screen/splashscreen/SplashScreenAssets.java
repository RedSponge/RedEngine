package com.redsponge.redengine.screen.splashscreen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.AssetSpecifier;

public class SplashScreenAssets extends AssetSpecifier {

    public SplashScreenAssets(AssetManager am) {
        super(am);
    }

    @Asset("splashscreen/splashscreen.atlas")
    private TextureAtlas splashScreenAtlas;
}
