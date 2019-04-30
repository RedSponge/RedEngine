package com.redsponge.testgame;

import com.badlogic.gdx.graphics.Texture;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.IAssetRequirer;

public class MyAssetHolder implements IAssetRequirer {

    private AnotherAssetHolder anotherAssetHolder;

    @Asset(path = "noise.png")
    public Texture myTexture;

    @Asset(path = "world_tiles.png")
    public Texture worldTiles;

}
