package com.redsponge.redengine;

import com.redsponge.redengine.screen.SplashScreenScreen;

public class TestGame extends EngineGame {

    @Override
    public void init() {
        SplashScreenScreen s = new SplashScreenScreen(ga);
        assets.load(s);
        setScreen(s);
    }
}
