package com.redsponge.redengine.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

@SuppressWarnings("ALL")
public class Fonts {

    public static BitmapFont pixelMix16;
    public static BitmapFont pixelMix32;
    public static BitmapFont pixelVerdana16;
    public static BitmapFont pixelVerdana32;

    public static void load() {
        pixelMix16 = new BitmapFont(Gdx.files.internal("fonts/pixelmix/16/pixelmix_16.fnt"));
        pixelMix32 = new BitmapFont(Gdx.files.internal("fonts/pixelmix/32/pixelmix_32.fnt"));
        pixelVerdana16 = new BitmapFont(Gdx.files.internal("fonts/pixelverdana/16/pixel_verdana_16.fnt"));
        pixelVerdana32 = new BitmapFont(Gdx.files.internal("fonts/pixelverdana/32/pixel_verdana_32.fnt"));
    }

    public static void unload() {
        pixelMix16.dispose();
        pixelMix32.dispose();
        pixelVerdana16.dispose();
        pixelVerdana32.dispose();
    }
}
