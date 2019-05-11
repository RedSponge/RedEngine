package com.redsponge.redengine.light;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.redengine.assets.Assets;

public interface Light {

    void update(float delta);

    void render(SpriteBatch batch);

    void load(Assets assets);

    float getRadius();

    Vector2 getPosition();
}
