package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * A class containing all textures related to light
 */
public class LightTextures implements Disposable {

    private static LightTextures instance;

    public static LightTextures getInstance() {
        if(instance == null) {
            instance = new LightTextures();
        }
        return instance;
    }

    public final Texture featheredPointLight;
    public final Texture flatPointLight;
    public final Texture starPointLight;

    private LightTextures() {
        featheredPointLight = new Texture("light/feathered_point_light.png");
        flatPointLight = new Texture("light/flat_point_light.png");
        starPointLight = new Texture("light/star_point_light.png");
    }

    public void dispose() {
        featheredPointLight.dispose();
        flatPointLight.dispose();
        starPointLight.dispose();
    }

    public static void disposeAssets() {
        if(instance != null) {
            instance.dispose();
        }
    }

}
