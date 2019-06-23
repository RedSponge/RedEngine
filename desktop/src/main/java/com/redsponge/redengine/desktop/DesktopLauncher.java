package com.redsponge.redengine.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.redsponge.redengine.DefaultEngineGame;
import org.lwjgl.opengl.Display;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
    public static void main(String[] args) {
        createApplication();
    }

    private static LwjglApplication createApplication() {
        return new LwjglApplication(new DefaultEngineGame(true, Display::setLocation), getDefaultConfiguration());
    }

    private static LwjglApplicationConfiguration getDefaultConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "EngineGame";
        for (int size : new int[] { 128, 64, 32, 16 }) {
            configuration.addIcon("libgdx" + size + ".png", FileType.Internal);
        }
        configuration.resizable = false;
        return configuration;
    }
}