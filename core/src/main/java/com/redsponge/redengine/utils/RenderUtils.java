package com.redsponge.redengine.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class RenderUtils {

    public static void renderTextCentered(SpriteBatch batch, BitmapFont font, CharSequence text, float x, float y) {
        GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, text, x - layout.width / 2, y - layout.height / 2, layout.width, Align.center, true);
    }
}
