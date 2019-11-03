package com.redsponge.test.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.redsponge.redengine.screen.components.AnimationComponent;
import com.redsponge.redengine.screen.components.PositionComponent;
import com.redsponge.redengine.screen.components.TextureComponent;
import com.redsponge.redengine.screen.entity.ScreenEntity;

public class PlatformerPlayer extends ScreenEntity {

    private AnimationComponent anim;

    @Override
    public void added() {
        pos.set(100, 100);
        size.set(16, 32);
    }

    @Override
    public void loadAssets() {
        anim = new AnimationComponent(assets.getAnimation("idle"));
        add(anim);
    }

    @Override
    public void additionalTick(float delta) {
        boolean right = Gdx.input.isKeyPressed(Keys.RIGHT);
        boolean left = Gdx.input.isKeyPressed(Keys.LEFT);
        boolean up = Gdx.input.isKeyPressed(Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Keys.DOWN);

        int vx = (right ? 1 : 0) - (left ? 1 : 0);

        vel.setX(vx * 100);
        if(vx != 0) {
            render.setFlipX(vx < 0);
        }
    }
}
