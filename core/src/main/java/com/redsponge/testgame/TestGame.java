package com.redsponge.testgame;

import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.light.LightTestScreen;

public class TestGame extends EngineGame {

    @Override
    public void init() {
        setScreen(new LightTestScreen(ga));
    }
}
