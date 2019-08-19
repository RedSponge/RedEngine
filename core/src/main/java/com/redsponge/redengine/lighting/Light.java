package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents a singular light, can be added to {@link LightSystem}s
 */
public interface Light {

    /**
     * Renders the light, will usually be called by a {@link LightSystem} to be drawn on the light-map
     * @param ls The calling light-system
     * @param batch The batch to use to draw
     * @param renderedViewport The viewport to render on, its camera will transform the light's position
     */
    void render(LightSystem ls, SpriteBatch batch, Viewport renderedViewport);

}
