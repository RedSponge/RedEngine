package com.redsponge.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.redengine.lighting.LightSystem;
import com.redsponge.redengine.lighting.LightTextures;
import com.redsponge.redengine.lighting.LightType;
import com.redsponge.redengine.lighting.PointLight;
import com.redsponge.redengine.screen.entity.ScreenEntity;

public class EntityDude extends ScreenEntity {

    private Texture texture;
    private Vector2 pos;

    private PointLight light;
    private PointLight innerLight;

    private LightSystem ls;

    public EntityDude(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super(batch, shapeRenderer);
    }

    @Override
    public void added() {
        pos = new Vector2(50, 50);

        ls = screen.getSystem(LightSystem.class);

        light = new PointLight(50, 50, 100, LightTextures.Point.feathered);
        ls.addLight(light, LightType.MULTIPLICATIVE);

        innerLight = new PointLight(50, 50, 50, LightTextures.Point.feathered);
        innerLight.getColor().set(0.2f, 0.2f, 0.2f, 1.0f);

//        ls.addLight(innerLight, LightType.ADDITIVE);
    }

    @Override
    public void loadAssets() {
        texture = assets.get("happy", Texture.class);
    }

    @Override
    public void tick(float delta) {
        float speed = 200;
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            pos.x += speed * delta;
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            pos.x -= speed * delta;
        }
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            pos.y += speed * delta;
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            pos.y -= speed * delta;
        }

        light.pos.set(pos);
        innerLight.pos.set(pos);
    }

    @Override
    public void render() {
        batch.draw(texture, pos.x - 16, pos.y - 16, 32, 32);
    }

    @Override
    public void removed() {
        screen.getSystem(LightSystem.class).removeLight(light, LightType.MULTIPLICATIVE);
    }
}
