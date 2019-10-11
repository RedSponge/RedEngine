package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;

public class SizeComponent implements Component {

    private int x;
    private int y;

    public SizeComponent() {
    }

    public SizeComponent(int x, int y) {
        this.x = x;
        this.y = y;
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
}
