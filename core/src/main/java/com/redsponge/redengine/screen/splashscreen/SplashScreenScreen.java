package com.redsponge.redengine.screen.splashscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.transitions.Transition;
import com.redsponge.redengine.utils.GameAccessor;

/**
 * Splash Screen - Renders the RedSponge splashscreen using a {@link SplashScreenRenderer}
 */
public class SplashScreenScreen extends AbstractScreen {

    private SplashScreenRenderer splashScreenRenderer;
    private boolean skipped;
    private AbstractScreen nextScreen;
    private Transition transition;

    public SplashScreenScreen(GameAccessor ga, AbstractScreen nextScreen, Transition transition) {
        super(ga);
        this.nextScreen = nextScreen;
        this.transition = transition;
    }

    @Override
    public void show() {
        splashScreenRenderer = new SplashScreenRenderer(batch, assets);

        splashScreenRenderer.begin();
        skipped = false;
    }

    @Override
    public void tick(float delta) {
        if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            skipped = true;
        }

        if(!skipped) {
            splashScreenRenderer.tick(delta);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        splashScreenRenderer.render();

        if((splashScreenRenderer.isComplete() || skipped) && !transitioning) {
            ga.transitionTo(nextScreen, transition);
        }
    }



    @Override
    public void reSize(int width, int height) {
        splashScreenRenderer.resize(width, height);
    }

    @Override
    public void disposeAssets() {
        splashScreenRenderer.dispose();
    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return SplashScreenAssets.class;
    }
}