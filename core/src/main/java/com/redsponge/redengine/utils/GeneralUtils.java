package com.redsponge.redengine.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.scenario.Settings;

import java.lang.reflect.Array;
import java.util.Random;

public class GeneralUtils {

    private static final Random random = new Random();

    public static float secondsSince(long time) {
        return (TimeUtils.nanoTime() - time) / 1000000000f;
    }

    /**
     * Joins arrays into one big array
     * @param arrs The arrays to join into one
     * @param <T> The type
     * @return An array consisting of the two arrays joined.
     */
    @SafeVarargs
    public static <T> T[] joinArrays(Class<T> c, T[]... arrs) {
        int size = 0;
        for (T[] ts : arrs) {
            size += ts.length;
        }

        T[] out = (T[]) Array.newInstance(c, size);

        int offset = 0;
        for (T[] arr : arrs) {
            System.arraycopy(arr, 0, out, offset, arr.length);
            offset += arr.length;
        }
        return out;
    }

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

    /**
     * @return The username of the current logged in user
     */
    @SuppressWarnings("unused")
    public static String getCurrentUserName() {
        try {
            return System.getProperty("user.name");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Selects a random item from an array
     *
     * @param arr The array to select an item from
     * @param <T> The type of the array
     * @return A random item from that array
     */
    @SuppressWarnings("unused")
    public static <T> T randomItem(T[] arr) {
        return arr[MathUtils.random(arr.length - 1)];
    }

    /**
     * Capitalizes a string
     * For example: hello woRLd -> Hello World
     * @param str The string to capitalize
     * @return The capitalized string
     */
    @SuppressWarnings("unused")
    public static String capitalizeString(String str){
        String[] words = str.split("\\s");
        StringBuilder capitalizeWord= new StringBuilder();
        for(String w : words){
            String first = w.substring(0,1);
            String afterFirst = w.substring(1);
            capitalizeWord.append(first.toUpperCase()).append(afterFirst.toLowerCase()).append(" ");
        }
        return capitalizeWord.toString().trim();
    }
}
