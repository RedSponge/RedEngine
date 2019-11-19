package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;

public class SizeComponent implements Component {

    private int x;
    private int y;
    private boolean beenSet;

    public SizeComponent() {
        this(0, 0);
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
        this.beenSet = true;
        return this;
    }

    public int getY() {
        return y;
    }

    public SizeComponent setY(int y) {
        this.y = y;
        this.beenSet = true;
        return this;
    }

    public SizeComponent set(int x, int y) {
        this.x = x;
        this.y = y;
        this.beenSet = true;
        return this;
    }

    @Override
    public String toString() {
        return "SizeComponent{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean isBeenSet() {
        return beenSet;
    }

    public SizeComponent setBeenSet(boolean beenSet) {
        this.beenSet = beenSet;
        return this;
    }
}
