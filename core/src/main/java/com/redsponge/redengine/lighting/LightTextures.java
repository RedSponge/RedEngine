package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class LightTextures implements Disposable {

    private static LightTextures instance;

    public static LightTextures getInstance() {
        if(instance == null) {
            instance = new LightTextures();
        }
        return instance;
    }

    public final Texture pointLight;

    private LightTextures() {
        pointLight = new Texture("light/point_light.png");
    }

    public void dispose() {
        pointLight.dispose();
    }

    public static void disposeAssets() {
        if(instance != null) {
            instance.dispose();
        }
    }

}
