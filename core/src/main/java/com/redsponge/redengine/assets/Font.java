package com.redsponge.redengine.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.redengine.utils.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Font implements Disposable {

    private Map<Integer, BitmapFont> fonts;
    private String tffFile;

    private Set<Integer> sizesToAdd;

    public static boolean DEBUG_LOG = true;

    public Font(String tffFile) {
        this.tffFile = tffFile;
        sizesToAdd = new HashSet<>(16);
        fonts = new HashMap<>(16);
    }

    private void debugLog(Object... toLog) {
        if(DEBUG_LOG) {
            Logger.log(this, toLog);
        }
    }

    /**
     * Adds a size to be loaded in
     * @param size The size to add
     * @see Font#loadFonts() to load the size in.
     */
    public void addSize(int size) {
        if(fonts.containsKey(size)) {
            debugLog("Font already has size", size, "loaded");
            return;
        }
        sizesToAdd.add(size);
    }

    /**
     * Adds sizes to be loaded in
     * @param sizes The sizes to add
     * @see Font#loadFonts() to load the sizes in.
     */
    public void addSizes(int... sizes) {
        for (int i = 0; i < sizes.length; i++) {
            addSize(sizes[i]);
        }
    }

    /**
     * Loads all font sizes added
     * Use {@link Font#addSize(int)} and {@link Font#addSizes(int...)} to choose sizes to add
     */
    public void loadFonts() {
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(tffFile));
        FreeTypeFontParameter param = new FreeTypeFontParameter();
        param.color = Color.WHITE;

        for (int size : sizesToAdd) {
            param.size = size;
            BitmapFont font = gen.generateFont(param);
            fonts.put(size, font);
        }
        sizesToAdd.clear();

        gen.dispose();
    }

    /**
     * Gets a font by size if it is loaded.
     * @param size The size to get
     * @return The font
     * @throws RuntimeException if the font isn't loaded.
     */
    public BitmapFont getFont(int size) {
        if(!fonts.containsKey(size)) {
            if(sizesToAdd.contains(size)) {
                throw new RuntimeException("Font " + tffFile + " doesn't have size " + size + " loaded, but it seems you called addSize with this size. Make sure you call Font#loadFonts to load it.");
            }
            throw new RuntimeException("Font " + tffFile + " doesn't have size " + size + " loaded!");
        }

        return fonts.get(size);
    }

    @Override
    public void dispose() {
        for (BitmapFont value : fonts.values()) {
            value.dispose();
        }
        fonts.clear();
    }
}
