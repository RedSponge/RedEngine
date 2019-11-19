package com.redsponge.test.platformer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.assets.atlas.AtlasAnimation;

public class PlatformerAssets extends AssetSpecifier {

    public PlatformerAssets(AssetManager am) {
        super(am);
    }

    @Asset("entities/entities.atlas")
    private TextureAtlas player;

    @AtlasAnimation(animationName = "idle", length = 2, atlas = "player", frameDuration = 1/3f)
    private Animation<TextureRegion> idle;

    @AtlasAnimation(animationName = "walk", length = 2, atlas = "player", frameDuration = 1/3f)
    private Animation<TextureRegion> walk;

    @AtlasAnimation(animationName = "jump", length = 2, atlas = "player", frameDuration = 0.05f)
    private Animation<TextureRegion> jump;

    @Asset("pixel.png")
    private Texture pixel;
}
