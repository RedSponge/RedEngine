package com.redsponge.redengine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.input.InputTranslator;
import com.redsponge.redengine.input.MapInputTranslator;
import com.redsponge.redengine.map.MapEditor;
import com.redsponge.redengine.utils.GameAccessor;

public class MapEditScreen extends AbstractScreen{

    private MapEditor editor;
    private Viewport viewport;

    private InputTranslator input;
    private Stage stage;

    private InputMultiplexer multiplexer;

    public MapEditScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(512, 512);
        stage = new Stage(viewport, batch);

        editor = new MapEditor(viewport, 100, 100, 32);

        multiplexer = new InputMultiplexer(editor, stage);

        Gdx.input.setInputProcessor(multiplexer);
        input = new MapInputTranslator();
    }

    @Override
    public void tick(float delta) {
        viewport.getCamera().position.x += 200 * delta * ((OrthographicCamera) viewport.getCamera()).zoom * input.getHorizontal();
        viewport.getCamera().position.y -= 200 * delta * ((OrthographicCamera) viewport.getCamera()).zoom * input.getVertical();

        stage.act(delta);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        editor.render(batch, shapeRenderer);
        stage.draw();
    }

    @Override
    public AssetDescriptor[] getRequiredAssets() {
        return new AssetDescriptor[0];
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }
}
