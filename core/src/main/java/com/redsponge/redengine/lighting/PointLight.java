package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * A point-light implementation of the light, draws a circle shape with a defined radius.
 */
public class PointLight implements Light {

    public final Vector2 pos;
    private Color color = Color.WHITE;
    private float radius;

    public PointLight(float x, float y, float radius) {
        this.radius = radius;
        this.pos = new Vector2(x, y);
    }

    @Override
    public void render(LightSystem ls, SpriteBatch batch) {
        batch.draw(LightTextures.getInstance().pointLight, pos.x - radius / 2, pos.y - radius / 2, radius, radius);
    }
}
