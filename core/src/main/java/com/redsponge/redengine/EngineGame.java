package com.redsponge.redengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.redengine.assets.Assets;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

public abstract class EngineGame extends Game {

    protected GameAccessor ga;
    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;
    protected Assets assets;

    @Override
    public final void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        ga = new GameAccessor(this);
        assets = new Assets();

        init();
    }

    @Override
    public void setScreen(Screen screen) {
        assert screen instanceof AbstractScreen;
        setScreen((AbstractScreen) screen);
    }

    public void setScreen(AbstractScreen screen) {
        if(this.screen != null) {
            this.screen.hide();
            this.screen.dispose();
            assets.unload((AbstractScreen) this.screen);
        }
        this.screen = screen;
        this.assets.load(screen);
        this.screen.show();
        this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        final AbstractScreen currentScreen = (AbstractScreen) screen;

        assets.updateAssetManager();
        if(screen != null) {
            currentScreen.tick(Gdx.graphics.getDeltaTime());
            currentScreen.render();
        }
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