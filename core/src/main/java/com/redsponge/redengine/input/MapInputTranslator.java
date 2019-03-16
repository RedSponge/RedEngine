package com.redsponge.redengine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * An example of an {@link InputTranslator}
 */
public class MapInputTranslator implements InputTranslator {

    @Override
    public float getHorizontal() {
        int left = Gdx.input.isKeyPressed(Keys.A) ? 1 : 0;
        int right = Gdx.input.isKeyPressed(Keys.D) ? 1 : 0;

        return right - left;
    }

    @Override
    public float getVertical() {
        int down = Gdx.input.isKeyPressed(Keys.W) ? 1 : 0;
        int up = Gdx.input.isKeyPressed(Keys.S) ? 1 : 0;

        return up - down;
    }

    @Override
    public boolean isJumping() {
        return false;
    }

    @Override
    public boolean isJustJumping() {
        return false;
    }
}
