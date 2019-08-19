package com.redsponge.redengine.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import com.badlogic.gdx.utils.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * General utility methods
 */
public class GeneralUtils {

    private static final Random random = new Random();

    private static final HashMap<Class<?>, Class<?>> PRIMITIVES = new HashMap<>();

    static {
        PRIMITIVES.put(Integer.class, int.class);
        PRIMITIVES.put(Double.class, double.class);
        PRIMITIVES.put(Float.class, float.class);
        PRIMITIVES.put(Boolean.class, boolean.class);
        PRIMITIVES.put(Byte.class, byte.class);
        PRIMITIVES.put(Long.class, long.class);
        PRIMITIVES.put(Short.class, short.class);
        PRIMITIVES.put(Character.class, char.class);
    }

    public static Class<?> replaceWrappersWithPrimitives(Class<?> c) {
        return PRIMITIVES.getOrDefault(c, c);
    }

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

        T[] out = (T[]) java.lang.reflect.Array.newInstance(c, size);

        int offset = 0;
        for (T[] arr : arrs) {
            System.arraycopy(arr, 0, out, offset, arr.length);
            offset += arr.length;
        }
        return out;
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

    public static String fieldToString(Field field) {
        return String.format("%s#%s", field.getDeclaringClass().getSimpleName(), field.getName());
    }

    public static Animation<TextureRegion> getAnimation(TextureAtlas atlas, String name, int startsWith, int length, float duration, PlayMode playMode) {
        Array<TextureRegion> frames = new Array<TextureRegion>(length);
        for (int i = 0; i < length; i++) {
            frames.add(atlas.findRegion(name, i + startsWith));
        }
        return new Animation<TextureRegion>(duration, frames, playMode);
    }

    public static ShaderProgram tryLoadShader(FileHandle vertex, FileHandle fragment) {
        ShaderProgram shader = new ShaderProgram(vertex, fragment);
        if(!shader.isCompiled()) {
            shader.dispose();
            throw new RuntimeException("Couldn't Load Shader! Log:\n" + shader.getLog() + "\n");
        }
        return shader;
    }

    /**
     * Prints the stacktrace to this call
     */
    public static void showStackTrace() {
        new Exception().printStackTrace();
    }
}
