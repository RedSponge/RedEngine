package com.redsponge.redengine;

import com.redsponge.test.TestLightScreen;

import java.util.function.BiConsumer;

public class DefaultEngineGame extends EngineGame {

    public DefaultEngineGame(boolean desktop, BiConsumer<Integer, Integer> desktopMoveAction) {
        super(desktop, desktopMoveAction);
    }

    @Override
    public void init() {
        setScreen(new TestLightScreen(ga));
    }
}
