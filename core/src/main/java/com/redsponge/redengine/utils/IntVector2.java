package com.redsponge.redengine.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * A {@link Vector2} with integer only values
 */
public class IntVector2 {

    public int x, y;

    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVector2() {
        this(0, 0);
    }

    public IntVector2(Vector2 v) {
        this((int) v.x, (int) v.y);
    }

    /**
     * Adds an x and y to this vector
     * @param x The x to add
     * @param y The y to add
     * @return The vector for chaining
     */
    public IntVector2 add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Adds a vector to this vector
     * @param intVec2 The vector to add
     * @return The vector for chaining
     */
    public IntVector2 add(IntVector2 intVec2) {
        return add(intVec2.x, intVec2.y);
    }

    /**
     * Sets this vector's position to a given position
     * @param x The x position
     * @param y The y position
     * @return The vector for chaining
     */
    public IntVector2 set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Sets this vector's position to a given position
     * @param intVec2 The vector to set position to
     * @return The vector for chaining
     */
    public IntVector2 set(IntVector2 intVec2) {
        return set(intVec2.x, intVec2.y);
    }

    /**
     * Scales a vector by given numbers
     * @param x The x to scale in, float to allow fractions
     * @param y THe y to scale in, float to allow fractions
     * @return The vector for chaining
     */
    public IntVector2 scl(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    /**
     * Scales a vector by a scalar
     * @param scalar The scalar to scale in, float to allow fractions
     * @return The vector for chaining
     */
    public IntVector2 scl(float scalar) {
        return scl(scalar, scalar);
    }

    /**
     * Scales a vector by a vector
     * @param vec2 The vector to scale in, regular to allow fractions
     * @return The vector for chaining
     */
    public IntVector2 scl(Vector2 vec2) {
        return scl(vec2.x, vec2.y);
    }

    /**
     * Scales a vector by a vector
     * @param vec2 The vector to scale in
     * @return The vector for chaining
     */
    public IntVector2 scl(IntVector2 vec2) {
        return scl(vec2.x, vec2.y);
    }

    /**
     * Creates a clone of the vector
     * @return A clone of the vector
     */
    public IntVector2 copy() {
        return new IntVector2(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IntVector2) {
            IntVector2 vec2 = (IntVector2) obj;
            return vec2.x == this.x && vec2.y == this.y;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}