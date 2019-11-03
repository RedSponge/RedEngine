package com.redsponge.redengine.assets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.HashMap;

public class Fonts {

    public static final int[] DEFAULT_SIZES = {8, 16, 32, 48, 64};

    private static HashMap<String, Font> fonts;

    public static void init() {
        fonts = new HashMap<>();
    }

    public static void addFont(String file, String name, int... initialSizes) {
        Font f = new Font(file);
        f.addSizes(initialSizes);
        f.loadFonts();

        fonts.put(name, f);
    }

    public static void addFontSizes(String name, int... fontSizes) {
        Font f = getFont(name);

        f.addSizes(fontSizes);
        f.loadFonts();
    }

    public static Font getFont(String font) {
        return fonts.get(font);
    }

    public static BitmapFont getFont(String font, int size) {
        return fonts.get(font).getFont(size);
    }

    public static void unload() {
        for (Font value : fonts.values()) {
            value.dispose();
        }
        fonts.clear();
    }
}
