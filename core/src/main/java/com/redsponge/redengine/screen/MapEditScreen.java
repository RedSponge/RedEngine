package com.redsponge.redengine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.assets.AssetDescBin;
import com.redsponge.redengine.assets.AssetDescBin.Skins;
import com.redsponge.redengine.input.InputTranslator;
import com.redsponge.redengine.input.MapInputTranslator;
import com.redsponge.redengine.map.EraserTile;
import com.redsponge.redengine.map.MapEditor;
import com.redsponge.redengine.map.TileSelectButton;
import com.redsponge.redengine.map.TileSelector;
import com.redsponge.redengine.utils.GameAccessor;

public class MapEditScreen extends AbstractScreen{

    private MapEditor editor;

    private Viewport viewport;
    private Viewport guiViewport;

    private InputTranslator input;
    private Stage stage;

    private TileSelector tileSelector;

    private InputMultiplexer multiplexer;
    private Skin mapEditorSkin;

    private Window window;

    public MapEditScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(512, 512);
        guiViewport = new ExtendViewport(512, 512);

        stage = new Stage(guiViewport, batch);

        editor = new MapEditor(viewport, 100, 100, 32);

        multiplexer = new InputMultiplexer(stage, editor);

        Gdx.input.setInputProcessor(multiplexer);
        input = new MapInputTranslator();

        tileSelector = new TileSelector();
        stage.addActor(tileSelector);

        tileSelector.addActor(new TileSelectButton(new EraserTile(), editor));
        tileSelector.addActor(new TileSelectButton(editor.getGroups()[0], editor));
        tileSelector.addActor(new TileSelectButton(editor.getGroups()[1], editor));

        mapEditorSkin = assets.get(Skins.mapEditor);


        window = new Window("", mapEditorSkin);
        window.setDebug(true);
        window.setSize(400, 250);
        System.out.println(guiViewport.getWorldWidth());

        window.setPosition(guiViewport.getWorldWidth() - window.getWidth(), 0);

        Label title = new Label("Edit Event", mapEditorSkin);
        title.setHeight(window.getHeight() * 0.1f);
        title.setPosition(window.getWidth() / 2, window.getHeight() - title.getPrefHeight() - 5, Align.center);
        window.addActor(title);

        Table table = new Table();
        table.setDebug(true);
        table.add(new Label("Event Id: ", mapEditorSkin));
        table.add(new TextField("", mapEditorSkin));

        table.setFillParent(true);

        ScrollPane pane = new ScrollPane(table, mapEditorSkin);

        pane.setWidth(window.getWidth());
        pane.setHeight(window.getHeight() * 0.9f);
        window.addActor(pane);
        stage.addActor(window);

        window.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
            }
        });
    }

    @Override
    public void tick(float delta) {
        viewport.getCamera().position.x += 200 * delta * ((OrthographicCamera) viewport.getCamera()).zoom * input.getHorizontal();
        viewport.getCamera().position.y -= 200 * delta * ((OrthographicCamera) viewport.getCamera()).zoom * input.getVertical();

        if(Gdx.input.isKeyJustPressed(Keys.TAB)) {
            tileSelector.toggle();
        }

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

        guiViewport.apply();
        stage.draw();
    }

    @Override
    public AssetDescriptor[] getRequiredAssets() {
        return new AssetDescriptor[] {
                Skins.mapEditor
        };
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
        guiViewport.update(width, height, true);
        window.setPosition(guiViewport.getWorldWidth() - window.getWidth(), 0);
    }
}
