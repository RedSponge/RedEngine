package com.redsponge.redengine.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileGroup {

    private TextureRegion region;
    private TextureRegion[] regions;

    private int id;

    public TileGroup(int topLCornerX, int topLCornerY, TileBatch batch, int id) {
        this.id = id;
        int size = batch.getTileSize();

        regions = new TextureRegion[16];
        for(int i = 0; i < 16; i++) {
            regions[i] = new TextureRegion(batch.getTexture(), topLCornerX * size + (i % 4) * size, topLCornerY * size + (i / 4) * size, size, size);
        }
    }

    public TextureRegion getRegion(int x, int y, short[][] map) {
        if(map[y][x] != this.id) return null;

        byte mask = 0;


        boolean[][] neighbors = new boolean[3][3];

        for(int yy = -1; yy < 2; yy++) {
            for(int xx = -1; xx < 2; xx++) {
                if(xx == 0 && yy == 0) {
                    continue;
                }
                try {
                    neighbors[2 - (yy + 1)][xx + 1] = map[yy + y][xx + x] != 0;
                } catch (IndexOutOfBoundsException e) {
                    neighbors[yy+1][xx+1] = false;
                }
            }
        }

        if(neighbors[0][1]) {
            mask += 1;
        }
        if(neighbors[1][0]) {
            mask += 2;
        }
        if(neighbors[1][2]) {
            mask += 4;
        }
        if(neighbors[2][1]) {
            mask += 8;
        }

        return regions[mask];

        /*
        if(!neighbors[0][1]) {
            // Top
            if(!neighbors[1][2]) {
                return topR;
            } else if(!neighbors[1][0]) {
                return topL;
            }
            return top;
        } else if(!neighbors[2][1]) {
            // Bottom
            if(!neighbors[1][2]) {
                return bottomR;
            } else if(!neighbors[1][0]) {
                return bottomL;
            }
            return bottom;
        } else {
            // Center
            if(!neighbors[1][2]) {
                return centerR;
            } else if(!neighbors[1][0]) {
                return centerL;
            }
            return center;
        }*/
    }
}
