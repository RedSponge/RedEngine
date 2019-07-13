package com.redsponge.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.redengine.screen.INotified;
import com.redsponge.redengine.screen.ScreenEntity;

public class Background extends ScreenEntity implements INotified {

    private Texture background;
    private float time;

    public Background(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super(batch, shapeRenderer);
    }

    @Override
    public void loadAssets() {
        background = assets.get("background", Texture.class);
    }

    @Override
    public void tick(float delta) {
        time += delta;
        if(time > 2) {
            time -= 2;
            notifyScreen(Notifications.SCREEN_TWO_SECONDS);
        }
    }

    @Override
    public void render() {
        batch.draw(background, 0, 0, 500, 500);
    }

    @Override
    public void notified(int notification) {
        switch (notification) {
            case Notifications.PLAYER_MOVE_RIGHT:
                batch.setColor(Color.BLUE);
                break;
            case Notifications.PLAYER_MOVE_LEFT:
                batch.setColor(Color.WHITE);
        }
    }

    @Override
    public void removed() {

    }

    @Override
    public int getZ() {
        return -3;
    }
}
