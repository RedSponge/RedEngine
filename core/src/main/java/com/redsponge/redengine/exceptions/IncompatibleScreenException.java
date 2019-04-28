package com.redsponge.redengine.exceptions;

import com.badlogic.gdx.Screen;

public class IncompatibleScreenException extends RuntimeException {

    public IncompatibleScreenException(Class<? extends Screen> clazz) {
        super("Screen " + clazz.getName() + " does not extend com.redsponge.redengine.AbstractScreen!");
    }
}
