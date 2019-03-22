package com.redsponge.redengine.utils;

import com.badlogic.gdx.utils.TimeUtils;

public class GeneralUtils {

    public static float secondsSince(long time) {
        return (TimeUtils.nanoTime() - time) / 1000000000f;
    }

    /**
     * Joins two arrays into one new array
     * @param arr1 The first array to join
     * @param arr2 The second array to join
     * @param <T> The type
     * @return An array consisting of the two arrays joined.
     */
    public static <T> T[] joinArrays(T[] arr1, T[] arr2) {
        Object[] out = new Object[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, out, 0, arr1.length);
        System.arraycopy(arr2, 0, out, arr1.length, arr2.length);
        return (T[]) out;
    }

    public static boolean rectanglesIntersect(IntVector2 pos1, IntVector2 size1, IntVector2 pos2, IntVector2 size2) {
        return pos1.x < pos2.x + size2.x &&
                pos1.x + size1.x > pos2.x &&
                pos1.y < pos2.y + size2.y &&
                pos1.y + size1.y > pos2.y;
    }

    public static float lerp(float current, float to, float a) {
        return (1 - a) * current + a * to;
    }
}
