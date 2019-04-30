package com.redsponge.testgame;

import com.badlogic.gdx.graphics.Texture;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.DoNotLoad;
import com.redsponge.redengine.assets.IAssetRequirer;

public class AnotherAssetHolder implements IAssetRequirer {

    @Asset(path = "selector_background.png")
    public Texture mySecondTexture;

    @Asset(path = "event_tile.png")
    @DoNotLoad
    public Texture myThirdTexture;

}
