package com.redsponge.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EraserTile extends TileGroup {

    private Texture eraser;
    private TextureRegion region;

    public EraserTile() {
        super(0, 0, null, 0);
        eraser = new Texture("eraser.png");
        region = new TextureRegion(eraser);
    }

    @Override
    public TextureRegion getRepresentingRegion() {
        return region;
    }
}
