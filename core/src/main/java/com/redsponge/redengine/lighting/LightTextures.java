package com.redsponge.redengine.lighting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * A class containing all textures related to light
 */
public class LightTextures {


    public static void loadAssets() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("lights/lights.atlas"));
        Point.loadTextures(atlas);
        Soft.loadTextures(atlas);
    }

    public static void disposeAssets() {

    }

    private interface LightTextureHolder {
        void loadTextures(TextureAtlas atlas);
    }

    public static class Point {

        public static AtlasRegion feathered;
        public static AtlasRegion flat;
        public static AtlasRegion star;

        public static void loadTextures(TextureAtlas atlas) {
            feathered = atlas.findRegion("point/feathered");
            flat = atlas.findRegion("point/flat");
            star = atlas.findRegion("point/star");
        }
    }

    public static class Soft {

        public static AtlasRegion cone;
        public static AtlasRegion diagonal;

        public static void loadTextures(TextureAtlas atlas) {
            cone = atlas.findRegion("soft/cone");
            diagonal = atlas.findRegion("soft/diagonal");
        }
    }

}
