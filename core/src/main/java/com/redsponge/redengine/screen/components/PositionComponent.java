package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.function.Consumer;

public class PositionComponent implements Component {

    private float x;
    private float y;
    private int z;
    /**
     * If true, and the entity has an {@link PhysicsComponent}, its body will teleport there.
     */
    private boolean beenSet;

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
        this.beenSet = true;
        return this;
    }

    public float getY() {
        return y;
    }

    public PositionComponent setY(float y) {
        this.y = y;
        this.beenSet = true;
        return this;
    }

    public int getZ() {
        return z;
    }

    public PositionComponent setZ(int z) {
        this.z = z;
        this.beenSet = true;
        return this;
    }

    public PositionComponent set(int x, int y) {
        this.x = x;
        this.y = y;
        this.beenSet = true;
        return this;
    }

    public PositionComponent set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.beenSet = true;
        return this;
    }

    public boolean isBeenSet() {
        return beenSet;
    }

    public PositionComponent setBeenSet(boolean beenSet) {
        this.beenSet = beenSet;
        return this;
    }

    @Override
    public String toString() {
        return "PositionComponent{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
