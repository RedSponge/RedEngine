package com.redsponge.redengine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.redsponge.redengine.screen.splashscreen.SplashScreenRenderer;
import com.redsponge.redengine.transitions.TransitionFade;
import com.redsponge.redengine.transitions.TransitionLine;
import com.redsponge.redengine.utils.GameAccessor;

/**
 * Splash Screen - Renders the RedSponge splashscreen using a {@link SplashScreenRenderer}
 */
public class SplashScreenScreen extends AbstractScreen {

    private SplashScreenRenderer splashScreenRenderer;
    private boolean skipped;

    public SplashScreenScreen(GameAccessor ga) {
        super(ga);
        splashScreenRenderer = new SplashScreenRenderer(batch, assets);
    }

    @Override
    public void show() {
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
            ga.transitionTo(new OtherScreen(ga), new TransitionLine(), 1f, Interpolation.sineIn, Interpolation.sineIn);
        }
    }



    @Override
    public void resize(int width, int height) {
        splashScreenRenderer.resize(width, height);
    }

    @Override
    public AssetDescriptor[] getRequiredAssets() {
        return splashScreenRenderer.getRequiredAssets();
    }

    @Override
    public void dispose() {
        splashScreenRenderer.dispose();
    }
}