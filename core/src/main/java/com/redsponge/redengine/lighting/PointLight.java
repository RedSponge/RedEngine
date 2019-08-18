package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * A point-light implementation of the light, draws a circle shape with a defined radius.
 */
public class PointLight implements Light {

    public final Vector2 pos;
    private Color color = Color.WHITE;
    private float radius;

    private Texture texture;

    public PointLight(float x, float y, float radius) {
        this(x, y, radius, LightTextures.getInstance().flatPointLight);
    }

    public PointLight(float x, float y, float radius, Texture texture) {
        this.radius = radius;
        this.texture = texture;
        this.pos = new Vector2(x, y);
    }

    @Override
    public void render(LightSystem ls, SpriteBatch batch) {
        batch.setColor(color);
        batch.draw(texture, pos.x - radius / 2, pos.y - radius / 2, radius, radius);
    }

    public Color getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
