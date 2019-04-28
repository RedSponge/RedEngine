package com.redsponge.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class TileBatch implements Disposable {

    private Texture tiles;
    private int tileSize;

    private int width, height;

    private TextureRegion[] regions;

    public TileBatch(Texture tiles, int tileSize) {
        this.tiles = tiles;
        this.tileSize = tileSize;

        setupRegions();
    }

    private void setupRegions() {
        width = tiles.getWidth() / tileSize;
        height = tiles.getHeight() / tileSize;

        this.regions = new TextureRegion[width * height];

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                regions[y * width + x] = new TextureRegion(tiles, x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TextureRegion getTile(int index) {
        return regions[index];
    }

    @Override
    public void dispose() {
        tiles.dispose();
    }

    public Texture getTexture() {
        return tiles;
    }

    public int getTileSize() {
        return tileSize;
    }
}
