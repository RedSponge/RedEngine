package com.redsponge.redengine.light;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FlickeringPointLight extends PointLight {

    protected float flickerSpeed;
    protected float flickerSize;
    private float time;

    public FlickeringPointLight(float x, float y, float radius, float flickerSpeed, float flickerSize) {
        super(x, y, radius);
        this.flickerSpeed = flickerSpeed;
        this.flickerSize = flickerSize;
    }

    @Override
    public void update(float delta) {
        time += delta;
    }

    @Override
    public void render(SpriteBatch batch, Viewport viewport) {
        Vector2 pos = viewport.project(this.pos);
        float rad = (float) (radius + Math.sin(time * flickerSpeed) * flickerSize);
        batch.draw(light, pos.x - rad / 2, pos.y - rad / 2, rad, rad);
    }
}
