package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {

    private float x;
    private float y;

    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public VelocityComponent() {
        this(0, 0);
    }

    public float getX() {
        return x;
    }

    public VelocityComponent setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return y;
    }

    public VelocityComponent setY(float y) {
        this.y = y;
        return this;
    }

    public VelocityComponent set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
