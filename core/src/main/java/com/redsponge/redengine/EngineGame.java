package com.redsponge.redengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.redsponge.redengine.assets.Assets;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.transitions.Transition;
import com.redsponge.redengine.transitions.TransitionManager;
import com.redsponge.redengine.utils.GameAccessor;

public abstract class EngineGame extends Game {

    protected GameAccessor ga;
    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;
    protected Assets assets;
    protected TransitionManager transitionManager;


    @Override
    public final void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        ga = new GameAccessor(this);
        assets = new Assets();
        transitionManager = new TransitionManager(this, shapeRenderer);

        transitionManager.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        init();
    }

    public void transitionToScreen(AbstractScreen screen, Transition transition, float length, Interpolation interFrom, Interpolation interTo) {
        transitionManager.startTransition(screen, transition, length, interFrom, interTo);
        transitionManager.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void setScreen(Screen screen) {
        assert screen instanceof AbstractScreen;
        setScreen((AbstractScreen) screen);
    }

    public void setScreen(AbstractScreen screen) {
        if(this.screen != null) {
            this.screen.hide();
            if(((AbstractScreen) this.screen).shouldDispose()) {
                this.screen.dispose();
            }

            assets.unload((AbstractScreen) this.screen);
        }

        this.screen = screen;

        if(screen != null) {
            this.assets.load(screen);
            this.assets.finishLoading();

            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    @Override
    public void render() {
        final AbstractScreen currentScreen = (AbstractScreen) screen;

        assets.updateAssetManager();
        if(screen != null) {
            currentScreen.tick(Gdx.graphics.getDeltaTime());
            currentScreen.render();
        }

        if(transitionManager.isActive()) {
            transitionManager.render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        transitionManager.resize(width, height);
    }

    /**
     * Initialization - Called when the program boots up
     */
    public abstract void init();

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public Assets getAssets() {
        return assets;
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        assets.dispose();
    }
}