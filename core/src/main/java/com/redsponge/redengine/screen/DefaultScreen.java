package com.redsponge.redengine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.assets.Fonts;
import com.redsponge.redengine.desktop.DesktopUtil;
import com.redsponge.redengine.utils.GameAccessor;
import com.strongjoshua.console.GUIConsole;

public class DefaultScreen extends AbstractScreen {

    private BitmapFont font;
    private FitViewport viewport;
    private TypingLabel typingLabel;
    private Stage stage;

    private float timeSinceStart;

    public static final String TEXT = "{GRADIENT=red;orange;2;2}{WAVE=0.5;1;1}This is the default screen mate\n\nyou should probably extend your own";

    public DefaultScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {

        font = Fonts.getFont("pixelmix", 16);
        viewport = new FitViewport(640 ,360);
        stage = new Stage(viewport, batch);
        typingLabel = new TypingLabel(TEXT, new LabelStyle(font, Color.WHITE));

        String nonTagged = TEXT.replaceAll(" ?\\{.+}", "");
        GlyphLayout layout = new GlyphLayout(font, "{GRADIENT=red;orange;2;2}{WAVE=0.5;1;1}");
        typingLabel.setPosition(-layout.width / 2, viewport.getWorldHeight() / 2);
        typingLabel.setAlignment(Align.center);

        stage.addActor(typingLabel);
        timeSinceStart = 0;
    }

    @Override
    public void tick(float delta) {
        timeSinceStart += delta;

        stage.act(delta);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        stage.draw();
    }

    @Override
    public void reSize(int width, int height) {
        viewport.update(width, height, true);
//        console.refresh(true);
    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return null;
    }
}
