package com.redsponge.redengine.utils;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.redsponge.redengine.assets.Assets;
import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.transitions.Transition;
import com.redsponge.redengine.transitions.TransitionTemplate;

/**
 * Made to access some of the game's properties and methods without passing the game instance itself
 */
public class GameAccessor {

    private EngineGame game;

    public GameAccessor(EngineGame game) {
        this.game = game;
    }

    /**
     * Transitions to a new screen
     * @param to The new screen to transition to
     * @param template The template of the transition
     */
    public void transitionTo(AbstractScreen to, TransitionTemplate template) {
        transitionTo(to, template.transition, template.length, template.interFrom, template.interTo);
    }

    /**
     * Transitions to a new screen
     * @param to The new screen to transition to
     * @param transition The transition to use
     * @param length The length of the transition
     * @param interFrom The interpolation of the first part of the transition
     * @param interTo The interpolation of the second part of the transition
     */
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
