package com.redsponge.redengine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Holds all asset descriptors for the project
 */
public class AssetDescBin {

    public static final class Skins {
        public static final AssetDescriptor<Skin> mapEditor = new AssetDescriptor<Skin>("skins/editor/editor_skin.json", Skin.class);
    }

    public static final class SplashScreen {
        public static final AssetDescriptor<TextureAtlas> atlas = new AssetDescriptor<TextureAtlas>("splashscreen/splashscreen.atlas", TextureAtlas.class);
    }

    public static final class Fonts {
        public static final AssetDescriptor<BitmapFont> pixelmix = new AssetDescriptor<BitmapFont>("fonts/pixelmix.fnt", BitmapFont.class);
    }

}
