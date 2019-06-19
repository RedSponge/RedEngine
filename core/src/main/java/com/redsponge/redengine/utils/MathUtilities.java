package com.redsponge.redengine.utils;

public class MathUtilities {

    /**
     * Checks if two rectangles intersect
     * @param pos1 The bottom left corner of the first rectangle
     * @param size1 The size of the first rectangle
     * @param pos2 The bottom left corner of the second rectangle
     * @param size2 The size of the second rectangle
     * @return Do the rectangles intersect?
     */
    public static boolean rectanglesIntersect(IntVector2 pos1, IntVector2 size1, IntVector2 pos2, IntVector2 size2) {
        return pos1.x < pos2.x + size2.x &&
                pos1.x + size1.x > pos2.x &&
                pos1.y < pos2.y + size2.y &&
                pos1.y + size1.y > pos2.y;
    }

    /**
     * Executes a linear interpolation function
     * @param current The current value
     * @param to The desired value
     * @param a The alpha value
     * @return The new current value
     */
    public static float lerp(float current, float to, float a) {
        return (1 - a) * current + a * to;
    }

}
