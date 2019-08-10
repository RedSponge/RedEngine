package com.redsponge.redengine.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.redengine.assets.atlas.AtlasAnimation;
import com.redsponge.redengine.assets.atlas.AtlasFrame;
import com.redsponge.redengine.utils.GeneralUtils;
import com.redsponge.redengine.utils.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Specifies the assets that shall be loaded for a certain screen. To use it extend it and write as fields with
 * {@link Asset} annotations what you wish to load.
 */
public class AssetSpecifier implements Disposable {

    private AssetManager am;
    private HashMap<String, String> assets;
    /**
     * Contains animations and atlas regions
     */
    private HashMap<String, Object> atlasCuts;

    public AssetSpecifier(AssetManager am) {
        this.am = am;
        assets = new HashMap<>();
        atlasCuts = new HashMap<>();
    }

    /**
     * Loads all assets from this class's fields
     */
    public void load() {
        for (Field field : this.getClass().getDeclaredFields()) {
            Asset assetAnnotation = field.getAnnotation(Asset.class);
            if(assetAnnotation != null) {
                am.load(assetAnnotation.value(), field.getType());
                assets.put(field.getName(), assetAnnotation.value());
            }
        }
    }

    public void postLoad() {
        for (Field field : this.getClass().getDeclaredFields()) {
            AtlasFrame atlasFrame = field.getAnnotation(AtlasFrame.class);
            AtlasAnimation atlasAnimation = field.getAnnotation(AtlasAnimation.class);

            Object injected;

            if(atlasFrame != null) {
                TextureAtlas atlas = get(atlasFrame.atlas(), TextureAtlas.class);
                injected = atlas.findRegion(atlasFrame.frameName(), atlasFrame.index());
            }
            else if(atlasAnimation != null) {
                TextureAtlas atlas = get(atlasAnimation.atlas(), TextureAtlas.class);
                injected = GeneralUtils.getAnimation(atlas, atlasAnimation.animationName(), atlasAnimation.startsWith(), atlasAnimation.length(),
                        atlasAnimation.frameDuration(), atlasAnimation.playMode());
            } else {
                injected = null;
            }

            if(injected != null) {
                Logger.log(this, "Injecting Animation/Region Into " + GeneralUtils.fieldToString(field));
                atlasCuts.put(field.getName(), injected);
            }
        }
    }

    public <T> T get(String name, Class<T> clazz) {
        return am.get(assets.get(name), clazz);
    }

    public Animation<TextureRegion> getAnimation(String name) {
        Object animation = atlasCuts.get(name);
        if(animation instanceof Animation<?>) {
            return (Animation<TextureRegion>) animation;
        }
        throw new RuntimeException("Value at " + name + " is not an animation!");
    }

    public TextureRegion getTextureRegion(String name) {
        Object textureReg = atlasCuts.get(name);
        if(textureReg instanceof TextureRegion) {
            return (TextureRegion) textureReg;
        }
        throw new RuntimeException("Value at " + name + " is not a texture region!");
    }

    @Override
    public void dispose() {
        assets.clear();
        am.clear();
    }
}
