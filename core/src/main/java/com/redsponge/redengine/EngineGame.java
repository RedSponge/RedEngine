package com.redsponge.redengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.assets.Fonts;
import com.redsponge.redengine.desktop.DesktopUtil;
import com.redsponge.redengine.exceptions.IncompatibleScreenException;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.transitions.Transition;
import com.redsponge.redengine.transitions.TransitionManager;
import com.redsponge.redengine.transitions.TransitionTextures;
import com.redsponge.redengine.utils.Discord;
import com.redsponge.redengine.utils.GameAccessor;
import com.redsponge.redengine.render.util.ScreenFiller;

import java.util.function.BiConsumer;

public abstract class EngineGame extends Game {

    protected GameAccessor ga;
    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;
    protected AssetManager am;
    protected TransitionManager transitionManager;
    protected Discord discord;

    private boolean assetLoadingComplete;


    public EngineGame() {
    }

    public EngineGame(boolean desktop, BiConsumer<Integer, Integer> desktopMoveAction) {
        if(desktop) {
            DesktopUtil.init(desktopMoveAction);
        }
    }

    /**
     * Initialization - Called when the program boots up
     */
    public abstract void init();

    @Override
    public final void create() {
        ShaderProgram.pedantic = false;
        ScreenFiller.init();

        am = new AssetManager();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        ga = new GameAccessor(this);

        transitionManager = new TransitionManager(this);

        transitionManager.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        discord = new Discord("571763236807114753", "");
        Fonts.load();

        init();
    }

    @Override
    public void render() {
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f);
        // Try catch to keep intellij from freezing when error is detected
        try {

            if(!assetLoadingComplete) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                if(am.update()) {
                    AssetSpecifier specs = ((AbstractScreen) screen).getAssets();
                    if(specs != null) {
                        specs.postLoad();
                    }

                    assetLoadingComplete = true;
                    transitionManager.beginExit();

                    this.screen.show();
                    this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                }

                return;
            }

            final AbstractScreen currentScreen = (AbstractScreen) screen;

            am.update();
            if (screen != null) {
                currentScreen.tick(delta);
                currentScreen.render();
            }

            if (transitionManager.isActive()) {
                transitionManager.render(delta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    /**
     * Sets a transition to a new screen
     * @param screen The new screen to be displayed
     * @param transition The transition to use
     */
    public void transitionToScreen(AbstractScreen screen, Transition transition) {
        transitionManager.startTransition(screen, transition);
        transitionManager.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    /**
     * Sets a new screen for the game
     * @param screen The new screen, must extend {@link AbstractScreen}
     * @throws IncompatibleClassChangeError If the screen doesn't extend {@link AbstractScreen}
     */
    @Override
    public void setScreen(Screen screen) {
        if(!(screen instanceof AbstractScreen)) {
            throw new IncompatibleScreenException(screen.getClass());
        }

        setScreen((AbstractScreen) screen);
    }

    /**
     * Sets a new screen for the game and unloads the last one.
     * @param screen The new screen to load
     */
    public void setScreen(AbstractScreen screen) {
        if(this.screen != null) {
            this.screen.hide();
            if(((AbstractScreen) this.screen).shouldDispose()) {
                ((AbstractScreen) this.screen).unloadAssetHolder();
                this.screen.dispose();
            }
        }

        this.screen = screen;

        if(screen != null) {
            assetLoadingComplete = false;
            try {
                Class<? extends AssetSpecifier> specsClass = screen.getAssetSpecsType();
                if(specsClass != null) {
                    AssetSpecifier specs = specsClass.getConstructor(AssetManager.class).newInstance(am);
                    specs.load();

                    screen.setAssets(specs);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        ScreenFiller.resize(width, height);
        if(assetLoadingComplete) {
            super.resize(width, height);
        }
        transitionManager.resize(width, height);
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    @Override
    public void dispose() {
        setScreen(null);
        discord.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        am.clear();
        TransitionTextures.disposeInstance();
    }
}