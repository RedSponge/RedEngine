package com.redsponge.redengine.render.util;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class NoiseGenerator {

    public static Texture createNoiseTexture(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int h = MathUtils.random(256);
                pixmap.drawPixel(j, i, (h << 24) + (h << 16) + (h << 8) + 255);
            }
        }
        Texture tex = new Texture(pixmap);
        pixmap.dispose();
        return tex;
    }

}
