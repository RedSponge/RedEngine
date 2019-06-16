package com.redsponge.redengine.light;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.assets.Assets;

public class PointLight implements Light {

    protected Vector2 pos;
    protected float radius;
    protected Texture light;
    private Vector2 calculatedPos;
    protected Color color;

    public PointLight(float x, float y, float radius) {
        this.pos = new Vector2(x, y);
        this.radius = radius;
        calculatedPos = new Vector2();
        this.color = new Color(Color.WHITE);
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render(SpriteBatch batch, Viewport viewport) {
        getTransformedPosition(viewport, calculatedPos);
        batch.setColor(this.color);
        batch.draw(light, calculatedPos.x - radius / 2, calculatedPos.y - radius / 2, radius, radius);
        batch.setColor(Color.WHITE);
    }

    @Override
    public Vector2 getTransformedPosition(Viewport viewport, Vector2 outPos) {
        outPos.set(pos);
        outPos.x += viewport.getWorldWidth() / 2 - viewport.getCamera().position.x;
        outPos.y += viewport.getWorldHeight() / 2 - viewport.getCamera().position.y;

        return outPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
