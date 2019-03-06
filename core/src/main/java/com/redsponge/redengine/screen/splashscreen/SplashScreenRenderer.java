package com.redsponge.redengine.screen.splashscreen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SplashScreenRenderer implements Disposable {

    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private AssetManager am;
    private boolean complete;
    private Image icon;

    public SplashScreenRenderer(SpriteBatch batch) {
        this.batch = batch;
        this.am = new AssetManager();
    }

    public void begin() {
        this.viewport = new FitViewport(480, 480);
        this.am.load("splashscreen/splashscreen.atlas", TextureAtlas.class);
        this.stage = new Stage(viewport, batch);
        this.complete = false;

        this.am.finishLoading();

        float waitBeforeFallDown = 2;
        TextureAtlas atlas = this.am.get("splashscreen/splashscreen.atlas", TextureAtlas.class);

        icon = new Image(atlas.findRegion("icon"));

        icon.setSize(256, 256);
        icon.setOrigin(Align.center);
        icon.setPosition(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 3 * 2, Align.center);
        icon.setScale(0);

        icon.addAction(Actions.scaleTo(1, 1, 1, Interpolation.swingOut));
        icon.addAction(Actions.rotateTo(0, 1, Interpolation.swingOut));
        icon.addAction(Actions.delay(waitBeforeFallDown, Actions.moveBy(0, -500, 0.5f, Interpolation.swingIn)));
        stage.addActor(icon);

        int spaced = 0;
        float letterScale = 5;
        float letterY = 100;
        float letterSpace = 5;
        float delay = 0.1f;
        float floatUpBy = 10;

        for(int i = 1; i <= 9; i++) {
            TextureRegion region = atlas.findRegion("l" + i);
            Image letter = new Image(region);
            letter.setScale(letterScale);
            letter.setPosition(spaced + 10, letterY - floatUpBy);
            spaced += region.getRegionWidth() * letterScale + letterSpace;
            letter.getColor().a = 0;
            letter.addAction(Actions.delay(i * delay, Actions.fadeIn(0.5f, Interpolation.exp5)));
            letter.addAction(Actions.delay(i * delay, Actions.moveBy(0, floatUpBy, 0.5f, Interpolation.exp5)));
            letter.addAction(Actions.delay(waitBeforeFallDown, Actions.moveBy(0, -500, 0.5f, Interpolation.swingIn)));
            stage.addActor(letter);
        }

    }

    public void tick(float delta) {
        stage.act(delta);
    }

    public void render() {
        viewport.apply(true);

        stage.draw();

        if(icon.getActions().size == 0) {
            complete = true;
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    public void dispose() {
        this.am.dispose();
    }
}
