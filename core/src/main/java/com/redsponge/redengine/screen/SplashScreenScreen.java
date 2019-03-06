package com.redsponge.redengine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.redsponge.redengine.utils.GameAccessor;
import com.redsponge.redengine.screen.splashscreen.SplashScreenRenderer;
import com.redsponge.redengine.transitions.TransitionFade;

/**
 * Splash Screen - Renders the RedSponge splashscreen using a {@link SplashScreenRenderer}
 */
public class SplashScreenScreen extends AbstractScreen {
    private SplashScreenRenderer splashScreenRenderer;
    private ScalingViewport scalingViewport;
    private boolean didTransition;

    public SplashScreenScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        this.scalingViewport = new ScalingViewport(Scaling.fill, 1, 1);
        splashScreenRenderer = new SplashScreenRenderer(batch);
        splashScreenRenderer.begin();
        didTransition = true;
    }

    @Override
    public void tick(float delta) {
        splashScreenRenderer.tick(delta);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!splashScreenRenderer.isComplete()) {
            splashScreenRenderer.render();
        } else if(!transitioning) {
            ga.transitionTo(new OtherScreen(ga), new TransitionFade(), 2);
        }
    }

    @Override
    public void resize(int width, int height) {
        splashScreenRenderer.resize(width, height);
        scalingViewport.update(width, height, true);
    }

    @Override
    public AssetDescriptor[] getRequiredAssets() {
        return new AssetDescriptor[] {
                new AssetDescriptor<TextureAtlas>("splashscreen/splashscreen.atlas", TextureAtlas.class)
        };
    }

    @Override
    public void dispose() {
        splashScreenRenderer.dispose();
    }
}