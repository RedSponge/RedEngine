package com.redsponge.test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.assets.atlas.AtlasAnimation;

public class TestSpecs extends AssetSpecifier {
    public TestSpecs(AssetManager am) {
        super(am);
    }

    @Asset("test/game_textures.atlas")
    private TextureAtlas playerAtlas;

    @AtlasAnimation(animationName = "high/run", atlas = "playerAtlas", length = 12, frameDuration = 0.08f)
    private Animation<TextureRegion> playerRun;

    @Asset("test/untitled.png")
    private Texture background;
}
