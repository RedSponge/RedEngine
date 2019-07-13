package com.redsponge.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.redengine.screen.INotified;
import com.redsponge.redengine.screen.ScreenEntity;

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
    public void tick(float delta) {
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
    public void render() {
        batch.draw(runAnimation.getKeyFrame(time), pos.x, pos.y, 100, 100);
    }

    @Override
    public void notified(int notification) {
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
