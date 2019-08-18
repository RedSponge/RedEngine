package com.redsponge.test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.AssetSpecifier;

public class TestScreenLightAssets extends AssetSpecifier {

    public TestScreenLightAssets(AssetManager am) {
        super(am);
    }

    @Asset("masking/sad.png")
    private Texture sad;
}
