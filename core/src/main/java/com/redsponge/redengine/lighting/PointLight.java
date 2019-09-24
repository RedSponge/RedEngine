package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * A point-light implementation of the light, draws a circle shape with a defined radius.
 */
public class PointLight implements Light {

    private final Vector2 tmpPos;

    public final Vector2 pos;
    private Color color = Color.WHITE.cpy();
    private float radius;

    private AtlasRegion texture;

    public PointLight(float x, float y, float radius) {
        this(x, y, radius, LightTextures.Point.flat);
    }

    public PointLight(float x, float y, float radius, AtlasRegion texture) {
        this.radius = radius;
        this.texture = texture;
        this.pos = new Vector2(x, y);
        this.tmpPos = new Vector2();
    }

    @Override
    public void render(LightSystem ls, SpriteBatch batch, Viewport viewport) {
        transformPositionByViewport(viewport, pos, tmpPos);
//        Logger.log(this, radius, tmpPos);

        batch.setColor(color);
        batch.draw(texture, tmpPos.x - radius / 2, tmpPos.y - radius / 2, radius, radius);
    }

    private void transformPositionByViewport(Viewport viewport, Vector2 pos, Vector2 outPos) {
        outPos.set(pos);
//        outPos.x += viewport.getWorldWidth() / 2 /*- viewport.getCamera().position.x / 2*/;
//        outPos.y += viewport.getWorldHeight() / 2 /*- viewport.getCamera().position.y / 2*/;
    }

    public Color getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setTexture(AtlasRegion texture) {
        this.texture = texture;
    }

    public AtlasRegion getTexture() {
        return texture;
    }
}
