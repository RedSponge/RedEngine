package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponent implements Component {

    private boolean flipX, flipY;
    private float offsetX, offsetY;
    private TextureRegion region;

    public RenderComponent() {
        flipX = false;
        flipY = false;
        offsetX = 0;
        offsetY = 0;
        region = new TextureRegion();
    }

    public boolean isFlipX() {
        return flipX;
    }

    public RenderComponent setFlipX(boolean flipX) {
        this.flipX = flipX;
        return this;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public RenderComponent setFlipY(boolean flipY) {
        this.flipY = flipY;
        return this;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public RenderComponent setRegion(TextureRegion region) {
        this.region = region;
        return this;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public RenderComponent setOffsetX(float offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public RenderComponent setOffsetY(float offsetY) {
        this.offsetY = offsetY;
        return this;
    }

    @Override
    public String toString() {
        return "RenderComponent{" +
                "flipX=" + flipX +
                ", flipY=" + flipY +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                ", region=" + region +
                '}';
    }
}
