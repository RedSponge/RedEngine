package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.utils.Logger;

import java.util.HashMap;

public class LightSystem implements Disposable {

    private static final Color DEFAULT_AMBIANCE_COLOR = new Color(0, 0, 0, 0);

    private HashMap<LightType, LightMap> lightMaps;

    private FitViewport drawingViewport;

    private SpriteBatch batch;

    public LightSystem(int width, int height, SpriteBatch batch) {
        this.batch = batch;
        drawingViewport = new FitViewport(width, height);
        lightMaps = new HashMap<>();
    }

    public void unregisterLightType(LightType lightType) {
        lightMaps.get(lightType).dispose();
        lightMaps.remove(lightType);
    }

    public void registerLightType(LightType lightType) {
        if(lightMaps.containsKey(lightType)) {
            unregisterLightType(lightType);
            Logger.log(this, "Warning: Light type", lightType, "is already registered! Recreating!");
        }

        lightMaps.put(lightType, new LightMap(lightType, (int) drawingViewport.getWorldWidth(), (int) drawingViewport.getWorldHeight()));
    }

    public void addLight(Light light, LightType lightType) {
        lightMaps.get(lightType).addLight(light);
    }

    public void removeLight(Light light, LightType lightType) {
        lightMaps.get(lightType).removeLight(light);
    }

    /**
     * Draws on the light-map to prepare it for rendering
     */
    public void prepareMap(LightType lightType) {
        lightMaps.get(lightType).prepareMap(this, batch);
    }

    public FitViewport getDrawingViewport() {
        return drawingViewport;
    }

    /**
     * Renders the light-map onto the screen with the correct blending, requires batch to be stopped
     */
    public void renderToScreen(LightType lightType) {
        lightMaps.get(lightType).renderMap(this, batch);
    }

    public void setAmbianceColor(Color ambianceColor, LightType lightType) {
        lightMaps.get(lightType).setAmbianceColor(ambianceColor);
    }

    public void resize(int width, int height) {
        drawingViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        lightMaps.forEach((l, m) -> {
            m.dispose();
            lightMaps.remove(l);
        });
    }
}
