package com.redsponge.redengine.utils;

import com.badlogic.gdx.utils.TimeUtils;

public class GeneralUtils {

    public static float secondsSince(long time) {
        return (TimeUtils.nanoTime() - time) / 1000000000f;
    }

    /**
     * Joins two arrays into one new array
     * @param arr1 - The first array to join
     * @param arr2 - The second array to join
     * @param <T> - The type
     * @return An array consisting of the two arrays joined.
     */
    public static <T> T[] joinArrays(T[] arr1, T[] arr2) {
        Object[] out = new Object[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, out, 0, arr1.length);
        System.arraycopy(arr2, 0, out, arr1.length, arr2.length);
        return (T[]) out;
    }

}
