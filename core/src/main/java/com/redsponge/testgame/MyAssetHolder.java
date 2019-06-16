package com.redsponge.testgame;

import com.badlogic.gdx.graphics.Texture;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.AssetRequirerAdapter;
import com.redsponge.redengine.assets.IAssetRequirer;
import com.redsponge.redengine.utils.Logger;

public class MyAssetHolder extends AssetRequirerAdapter {

    public AnotherAssetHolder anotherAssetHolder;

    @Asset(path = "noise.png", shouldDispose = false)
    public Texture myTexture;

    @Asset(path = "world_tiles.png")
    public Texture worldTiles;
}
