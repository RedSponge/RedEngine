package com.redsponge.testgame;

import com.badlogic.gdx.graphics.Texture;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.IAssetRequirer;

public class AnotherAssetHolder implements IAssetRequirer {

    @Asset(path = "selector_background.png")
    public Texture mySecondTexture;

    @Asset(path = "event_tile.png")
    public Texture myThirdTexture;

    @Override
    public void onAssetsLoaded() {

    }

    @Override
    public void onAssetsUnloaded() {

    }
}
