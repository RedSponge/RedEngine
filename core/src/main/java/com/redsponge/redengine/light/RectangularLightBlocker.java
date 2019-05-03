package com.redsponge.redengine.light;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RectangularLightBlocker implements LightBlocker {

    private float x, y, w, h;

    public RectangularLightBlocker(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(x, y, w, h);
    }
}
