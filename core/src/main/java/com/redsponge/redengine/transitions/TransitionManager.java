package com.redsponge.redengine.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.Logger;

/**
 * The transition manager, renders and handles screen switches with transitions
 */
public class TransitionManager {

    private Transition transition;
    private AbstractScreen pendingScreen;


    private boolean switched;
    private boolean transitioning;

    private boolean shouldProcessExit;
    private float timeCounter;

    private EngineGame game;

    public TransitionManager(EngineGame game) {
        this.game = game;
    }

    public void render(float delta) {
        if(!switched || shouldProcessExit) {
            timeCounter += delta;
        }

        float timeSince = timeCounter;


        if(timeSince > transition.getLength() / 2 && !switched) {
            Logger.log(this, "Transition Switched!!");
            game.setScreen(pendingScreen);
            switched = true;
            shouldProcessExit = true;

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }

        if(transition.isDone(timeSince) && transitioning) {
            transition.dispose();
            transition = null;
            transitioning = false;
            Logger.log(this, "Transition Complete!!");
        }

        if(transition != null) {
            transition.render(timeSince);
        }
    }

    public void beginExit() {
        shouldProcessExit = true;
    }

    public void startTransition(AbstractScreen next, Transition transition) {
        this.pendingScreen = next;
        this.transition = transition;
        this.switched = false;
        this.transitioning = true;
        this.shouldProcessExit = false;
        this.timeCounter = 0;
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
