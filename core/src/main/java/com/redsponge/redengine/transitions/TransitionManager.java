package com.redsponge.redengine.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.TimeUtils;
import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GeneralUtils;
import com.redsponge.redengine.utils.Logger;

public class TransitionManager {

    private Transition transition;

    private Interpolation interFrom, interTo;

    private AbstractScreen pendingScreen;

    private ShapeRenderer shapeRenderer;

    private long transitionBegin;
    private float length;

    private boolean switched;
    private boolean transitioning;

    private EngineGame game;

    public TransitionManager(EngineGame game, ShapeRenderer shapeRenderer) {
        this.game = game;
        this.shapeRenderer = shapeRenderer;
    }

    public void render(float delta) {
        float timeSince = GeneralUtils.secondsSince(transitionBegin);


        if(timeSince > length / 2 && !switched) {
            Logger.log(this, "Transition Switched!!");
            game.setScreen(pendingScreen);
            switched = true;

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }

        if(timeSince > length && transitioning) {
            transition = null;
            transitioning = false;
            Logger.log(this, "Transition Complete!!");
        }

        if(transition != null) {
            transition.render(timeSince, interFrom, interTo, length, shapeRenderer);
        }
    }

    public void startTransition(AbstractScreen next, Transition transition, float length, Interpolation interFrom, Interpolation interTo) {
        this.pendingScreen = next;
        this.transition = transition;
        this.length = length;
        this.transitionBegin = TimeUtils.nanoTime();
        this.switched = false;
        this.transitioning = true;
        this.interFrom = interFrom;
        this.interTo = interTo;
    }

    public void resize(int width, int height) {
        if(transition != null) {
            transition.resize(width, height);
        }
    }

    public boolean isActive() {
        return transition != null;
    }
}
