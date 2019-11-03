package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;

public class SizeComponent implements Component {

    private int x;
    private int y;
    private float scaleX;
    private float scaleY;

    public SizeComponent() {
        this(0, 0);
    }

    public SizeComponent(int x, int y, float scaleX, float scaleY) {
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public SizeComponent(int x, int y) {
        this(x, y, 1, 1);
    }

    public int getX() {
        return x;
    }

    public SizeComponent setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public SizeComponent setY(int y) {
        this.y = y;
        return this;
    }

    public SizeComponent set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public SizeComponent setScaleX(float scaleX) {
        this.scaleX = scaleX;
        return this;
    }

    public SizeComponent setScaleY(float scaleY) {
        this.scaleY = scaleY;
        return this;
    }

    public SizeComponent setScale(float x, float y) {
        this.scaleX = x;
        this.scaleY = y;
        return this;
    }

    public int getScaledX() {
        return (int) (x * scaleX);
    }

    public int getScaledY() {
        return (int) (y * scaleY);
    }

    public float getScaleX() {
        return scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    @Override
    public String toString() {
        return "SizeComponent{" +
                "x=" + x +
                ", y=" + y +
                ", scaleX=" + scaleX +
                ", scaleY=" + scaleY +
                '}';
    }
}
