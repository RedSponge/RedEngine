package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {

    private float x;
    private float y;
    private int z;

    public PositionComponent() {
        this(0, 0, 0);
    }

    public PositionComponent(int x, int y) {
        this(x, y, 0);
    }

    public PositionComponent(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public PositionComponent setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return y;
    }

    public PositionComponent setY(float y) {
        this.y = y;
        return this;
    }

    public int getZ() {
        return z;
    }

    public PositionComponent setZ(int z) {
        this.z = z;
        return this;
    }

    public PositionComponent set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
