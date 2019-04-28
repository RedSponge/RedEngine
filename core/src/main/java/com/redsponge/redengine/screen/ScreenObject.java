package com.redsponge.redengine.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class ScreenObject {

    public abstract void tick(float delta);

    public abstract void render(SpriteBatch batch, ShapeRenderer shapeRenderer);

}
