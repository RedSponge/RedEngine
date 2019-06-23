package com.redsponge.redengine.desktop;

import com.badlogic.gdx.Gdx;
import com.redsponge.redengine.utils.Logger;

import java.util.function.BiConsumer;

/**
 * A few methods which apply only for desktop. Things to do with the window to be exact
 */
public class DesktopUtil {

    private static BiConsumer<Integer, Integer> desktopMoveAction;
    private static boolean isDesktop;

    /**
     * Initiates and allows usage of the DesktopUtil class, must have the methods used by this class
     * @param desktopMoveAction The action for {@link DesktopUtil#moveWindow(int, int)}, should move the window when invoked
     */
    public static void init(BiConsumer<Integer, Integer> desktopMoveAction) {
        isDesktop = true;
        DesktopUtil.desktopMoveAction = desktopMoveAction;
    }

    /**
     * Resizes the screen, goes out of full-screen if it's on
     * @param width The new width
     * @param height The new height
     */
    public static void resize(int width, int height) {
        if(!isDesktop) {
            Logger.log(DesktopUtil.class, "Call to resize but not desktop!");
            return;
        }

        Gdx.graphics.setWindowedMode(width, height);
    }

    /**
     * Toggles full-screen
     * @param width The width of the window if going out of full-screen
     * @param height The height of the window if going out of full-screne
     */
    public static void toggleFullscreen(int width, int height) {
        if(!isDesktop) {
            Logger.log(DesktopUtil.class, "Call to toggleFullscreen but not desktop!");
            return;
        }

        if(Gdx.graphics.isFullscreen()) {
            resize(width, height);
        } else {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }
    }

    /**
     * Moves the game window on the screen
     * @param x The new game position x
     * @param y The new game position y
     */
    public static void moveWindow(int x, int y) {
        if(!isDesktop) {
            Logger.log(DesktopUtil.class, "Call to moveWindow but not desktop!");
            return;
        }

        desktopMoveAction.accept(x, y);
    }

    /**
     * Sets the title of the window
     * @param title The new title
     */
    public static void setTitle(String title) {
        if(!isDesktop) {
            Logger.log(DesktopUtil.class, "Call to setTitle but not desktop!");
            return;
        }

        Gdx.graphics.setTitle(title);
    }
}
