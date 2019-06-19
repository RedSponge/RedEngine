package com.redsponge.redengine;

import com.redsponge.redengine.screen.DefaultScreen;

public class DefaultEngineGame extends EngineGame {

    @Override
    public void init() {
        setScreen(new DefaultScreen(ga));
    }
}
