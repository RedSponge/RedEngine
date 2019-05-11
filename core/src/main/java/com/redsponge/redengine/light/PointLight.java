package com.redsponge.redengine.light;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.redengine.assets.Assets;

public class PointLight implements Light {

    protected Vector2 pos;
    protected float radius;
    protected Texture light;

    public PointLight(float x, float y, float radius) {
        this.pos = new Vector2(x, y);
        this.radius = radius;
    }

    @Override
    public void update(float delta) {}

    public void render(SpriteBatch batch) {
        batch.draw(light, pos.x - radius / 2, pos.y - radius / 2, radius, radius);
    }

    @Override
    public void load(Assets assets) {
        light = assets.get("light/point_light.png", Texture.class);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public Vector2 getPosition() {
        return pos;
    }
}
