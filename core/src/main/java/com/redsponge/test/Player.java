package com.redsponge.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.redengine.screen.INotified;
import com.redsponge.redengine.screen.entity.ScreenEntity;
import com.redsponge.redengine.utils.GameAccessor;

public class Player extends ScreenEntity implements INotified {

    private Animation<TextureRegion> runAnimation;
    private float time;
    private Vector2 pos;

    public Player(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super(batch, shapeRenderer);
        pos = new Vector2(100, 100);
    }

    @Override
    public void loadAssets() {
        runAnimation = assets.getAnimation("playerRun");
    }

    @Override
    public void additionalTick(float delta) {
        time += delta;
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            pos.x += 200 * delta;
            notifyScreen(Notifications.PLAYER_MOVE_RIGHT);
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            pos.x -= 200 * delta;
            notifyScreen(Notifications.PLAYER_MOVE_LEFT);
        }
    }

    @Override
    public void additionalRender() {
        batch.draw(runAnimation.getKeyFrame(time), pos.x, pos.y, 100, 100);
    }

    @Override
    public void notified(Object notifier, int notification) {
        switch (notification) {
            case Notifications.SCREEN_TWO_SECONDS:
                pos.y += 50;
                break;
        }
    }

    @Override
    public void removed() {

    }
}
