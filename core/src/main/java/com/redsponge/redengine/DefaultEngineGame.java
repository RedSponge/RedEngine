package com.redsponge.redengine;

import com.redsponge.redengine.screen.DefaultScreen;
import com.redsponge.test.TestLightScreen;
import com.redsponge.test.TestPScreen;
import com.redsponge.test.platformer.PlatformerScreen;

import java.util.function.BiConsumer;

public class DefaultEngineGame extends EngineGame {

    public DefaultEngineGame(boolean desktop, BiConsumer<Integer, Integer> desktopMoveAction) {
        super(desktop, desktopMoveAction);
    }

    @Override
    public void init() {
        setScreen(new PlatformerScreen(ga));
    }
}
