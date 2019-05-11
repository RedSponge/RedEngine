package com.redsponge.redengine.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Float.NaN;

/**
 * A line which follows this function: f(x) = mx + b;
 */
public class Line {

    private float m;
    private float b;

    private boolean vertical;
    private float x;

    public Line(float m, float b) {
        this.m = m;
        this.b = b;
    }

    public Line(float x) {
        this.x = x;
        this.vertical = true;
        this.m = 0;
        this.b = NaN;
    }

    /**
     * Applies the function f(x) = mx + b on the parameter
     * @param x The x to apply the function on
     * @return The result y
     */
    public float apply(float x) {
        return m * x + b;
    }

    /**
     * Finds an x from a y
     * @param y The y
     * @return The x
     */
    public float findX(float y) {
        return (y - b) / m;
    }

    public Intersection findIntersection(Line to, Vector2 pos, float[] limits) {
        if(to.m == m) return null; // If parallel return null since no intersection
        float interX;
        float interY;
        if(to.vertical) {
            System.out.println("Given is vertical!" + to);
            interX = to.x;
            interY = apply(x);
        } else if(this.vertical) {
            interX = x;
            interY = to.apply(interX);
            System.out.println("I am vertical!" + this);
        } else {
            interX = (b - to.b) / (to.m - m);
            interY = apply(interX);
        }
        if(limits != null) {
            float leftX = Math.min(limits[0], limits[2]);
            float rightX = Math.max(limits[0], limits[2]);
            float topY = Math.max(limits[1], limits[3]);
            float bottomY = Math.min(limits[1], limits[3]);
            if(leftX > interX || rightX < interX || bottomY > interY || topY < interY) return null;
        }
        float dist = pos.dst(interX, interY);
        return new Intersection(interX, interY, dist);
    }

    /**
     * Calculates a line from a point and an angle
     * @param angle The angle in radians
     * @param x The x coord
     * @param y The y coord
     * @return The representing line
     */
    public static Line fromAngleAndPoint(float angle, float x, float y) {
        double m = Math.tan(angle);
        double b = y - m * x;
        return new Line((float) m, (float) b);
    }

    /**
     * Calculates a line from two points
     * @param x1 The first x
     * @param y1 The first y
     * @param x2 The second x
     * @param y2 The second y
     * @return The representing line
     */
    public static Line fromTwoPoints(float x1, float y1, float x2, float y2) {
        if(x1 == x2) return new Line(x1); // Vertical line

        float m = (y2 - y1) / (x2 - x1);
        float b = y1 - m * x1;
        return new Line(m, b);
    }

    @Override
    public String toString() {
        return "Line{" +
                "m=" + m +
                ", b=" + b +
                '}';
    }
}
