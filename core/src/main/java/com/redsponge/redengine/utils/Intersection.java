package com.redsponge.redengine.utils;

import com.badlogic.gdx.math.Vector2;

public class Intersection {

    private Vector2 point;
    private float distance;

    public Intersection(float x, float y, float distance) {
        this.point = new Vector2(x, y);
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public Vector2 getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "point=" + point +
                ", distance=" + distance +
                '}';
    }
}
