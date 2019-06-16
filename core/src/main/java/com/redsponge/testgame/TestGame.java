package com.redsponge.testgame;

import com.redsponge.redengine.EngineGame;

public class TestGame extends EngineGame {

    @Override
    public void init() {
        setScreen(new Screen1(ga));
    }
}
