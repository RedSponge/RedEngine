package com.redsponge.redengine.render.mask;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class MaskLayer {

    private FrameBuffer black;
    private FrameBuffer white;
    private FrameBuffer mask;
    private FrameBuffer result;

    public MaskLayer(int width, int height) {
        black = new FrameBuffer(Format.RGBA8888, width, height, false);
        white = new FrameBuffer(Format.RGBA8888, width, height, false);
        mask = new FrameBuffer(Format.RGBA8888, width, height, false);
    }

    public void render(SpriteBatch batch) {
        Texture black = this.black.getColorBufferTexture();
        Texture white = this.white.getColorBufferTexture();

        result.begin();

        result.end();
    }

    public FrameBuffer getBlack() {
        return black;
    }

    public FrameBuffer getWhite() {
        return white;
    }

    public FrameBuffer getMask() {
        return mask;
    }
}
