package com.redsponge.redengine.utils;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.redsponge.redengine.assets.Assets;
import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.transitions.Transition;

/**
 * Made to access some of the game's properties and methods without passing the game instance itself
 */
public class GameAccessor {

    private EngineGame game;

    public GameAccessor(EngineGame game) {
        this.game = game;
    }

    public void transitionTo(AbstractScreen to, Transition transition, float length, Interpolation interFrom, Interpolation interTo) {
        AbstractScreen s = (AbstractScreen) game.getScreen();
        s.beginTransition();
        game.transitionToScreen(to, transition, length, interFrom, interTo);
    }

    public SpriteBatch getSpriteBatch() {
        return game.getSpriteBatch();
    }

    public ShapeRenderer getShapeRenderer() {
        return game.getShapeRenderer();
    }

    public void setScreen(ScreenAdapter screen) {
        game.setScreen(screen);
    }

    public Assets getAssets() {
        return game.getAssets();
    }
}
