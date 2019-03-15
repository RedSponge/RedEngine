package com.redsponge.redengine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * An example of an {@link InputTranslator}
 */
public class SimpleInputTranslator implements InputTranslator {

    @Override
    public float getHorizontal() {
        int left = Gdx.input.isKeyPressed(Keys.LEFT) ? 1 : 0;
        int right = Gdx.input.isKeyPressed(Keys.RIGHT) ? 1 : 0;

        return right - left;
    }

    @Override
    public float getVertical() {
        int down = Gdx.input.isKeyPressed(Keys.DOWN) ? 1 : 0;
        int up = Gdx.input.isKeyPressed(Keys.UP) ? 1 : 0;

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
