package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponent implements Component {

    private boolean flipX, flipY;
    private float offsetX, offsetY;
    private float scaleX, scaleY;

    // Flags, if true, the region's width and height will be used instead of the entity's SizeComponent's size
    private boolean useRegW, useRegH;
    private TextureRegion region;

    private Color color;

    private RenderCentering centering;

    public RenderComponent() {
        flipX = false;
        flipY = false;
        offsetX = 0;
        offsetY = 0;
        region = new TextureRegion();
        useRegW = false;
        useRegH = false;
        scaleX = 1;
        scaleY = 1;

        color = Color.WHITE.cpy();
        centering = RenderCentering.BOTTOM_LEFT;
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

    public boolean isUseRegW() {
        return useRegW;
    }

    public RenderComponent setUseRegW(boolean useRegW) {
        this.useRegW = useRegW;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public RenderComponent setColor(Color color) {
        this.color.set(color);
        return this;
    }

    public RenderComponent setColor(float r, float g, float b, float a) {
        this.color.set(r, g, b, a);
        return this;
    }

    public RenderCentering getCentering() {
        return centering;
    }

    public RenderComponent setCentering(RenderCentering centering) {
        this.centering = centering;
        return this;
    }

    public boolean isUseRegH() {
        return useRegH;
    }

    public RenderComponent setUseRegH(boolean useRegH) {
        this.useRegH = useRegH;
        return this;
    }

    public float getScaleX() {
        return scaleX;
    }

    public RenderComponent setScaleX(float scaleX) {
        this.scaleX = scaleX;
        return this;
    }

    public float getScaleY() {
        return scaleY;
    }

    public RenderComponent setScaleY(float scaleY) {
        this.scaleY = scaleY;
        return this;
    }

    @Override
    public String toString() {
        return "RenderComponent{" +
                "flipX=" + flipX +
                ", flipY=" + flipY +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                ", scaleX=" + scaleX +
                ", scaleY=" + scaleY +
                ", useRegW=" + useRegW +
                ", useRegH=" + useRegH +
                ", region=" + region +
                ", color=" + color +
                '}';
    }
}
