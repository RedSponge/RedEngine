package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents a singular light, can be added to {@link LightSystem}s
 */
public interface Light {

    /**
     * Renders the light, will usually be called by a {@link LightSystem} to be drawn on the light-map
     * @param ls The calling light-system
     * @param batch The batch to use to draw
     */
    void render(LightSystem ls, SpriteBatch batch);

}
