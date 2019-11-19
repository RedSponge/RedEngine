package com.redsponge.redengine.screen.entity;

import com.redsponge.redengine.screen.components.RenderComponent;

public class RenderPreset {

    private float offsetX;
    private float offsetY;
    private float scaleX;
    private float scaleY;

    public RenderPreset() {
        scaleX = 1;
        scaleY = 1;
        offsetX = 0;
        offsetY = 0;
    }

    public RenderPreset(float offsetX, float offsetY, float scaleX, float scaleY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public RenderPreset setOffsetX(float offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public RenderPreset setOffsetY(float offsetY) {
        this.offsetY = offsetY;
        return this;
    }

    public float getScaleX() {
        return scaleX;
    }

    public RenderPreset setScaleX(float scaleX) {
        this.scaleX = scaleX;
        return this;
    }

    public float getScaleY() {
        return scaleY;
    }

    public RenderPreset setScaleY(float scaleY) {
        this.scaleY = scaleY;
        return this;
    }

    /**
     * Applies the preset to the component
     */
    public void applyTo(RenderComponent renderComponent) {
        renderComponent.setScaleX(scaleX).setScaleY(scaleY).setOffsetX(offsetX).setOffsetY(offsetY);
    }

    /**
     * Extracts the component into the preset
     */
    public void extract(RenderComponent renderComponent) {
        this.scaleX = renderComponent.getScaleX();
        this.scaleY = renderComponent.getScaleY();
        this.offsetX = renderComponent.getOffsetX();
        this.offsetY = renderComponent.getOffsetY();
    }
}
