package com.redsponge.redengine.light;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FlickeringPointLight extends PointLight {

    protected float flickerSpeed;
    protected float flickerSize;
    private float time;
    private Vector2 calculatedPos;

    public FlickeringPointLight(float x, float y, float radius, float flickerSpeed, float flickerSize) {
        super(x, y, radius);
        this.flickerSpeed = flickerSpeed;
        this.flickerSize = flickerSize;
        this.calculatedPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        time += delta;
    }

    @Override
    public void render(SpriteBatch batch, Viewport viewport) {
        getTransformedPosition(viewport, calculatedPos);
        float rad = (float) (radius + Math.sin(time * flickerSpeed) * flickerSize);
        batch.setColor(color);
        batch.draw(light, calculatedPos.x - rad / 2, calculatedPos.y - rad / 2, rad, rad);
        batch.setColor(Color.WHITE);
    }
}
