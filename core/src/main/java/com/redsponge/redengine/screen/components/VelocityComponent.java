package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {

    private int x;
    private int y;

    public VelocityComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public VelocityComponent() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public VelocityComponent setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public VelocityComponent setY(int y) {
        this.y = y;
        return this;
    }

    public VelocityComponent set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
