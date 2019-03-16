package com.redsponge.testgame;

import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.screen.MapEditScreen;

public class TestGame extends EngineGame {

    @Override
    public void init() {
        setScreen(new MapEditScreen(ga));
    }
}
