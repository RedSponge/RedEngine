package com.redsponge.redengine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.map.EraserTile;
import com.redsponge.map.MapEditor;
import com.redsponge.map.TileSelectButton;
import com.redsponge.map.TileSelector;
import com.redsponge.map.events.EventChangeListener;
import com.redsponge.map.events.EventEditor;
import com.redsponge.map.events.EventTile;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.input.InputTranslator;
import com.redsponge.redengine.input.MapInputTranslator;
import com.redsponge.redengine.utils.GameAccessor;
import com.redsponge.redengine.utils.Logger;

public class MapEditScreen extends AbstractScreen{

    private MapEditor editor;

    private Viewport viewport;
    private Viewport guiViewport;

    private InputTranslator input;
    private Stage stage;

    private TileSelector tileSelector;

    private InputMultiplexer multiplexer;
    private EventEditor eventEditor;

    @Asset(path = "skins/editor/editor_skin.json")
    private Skin mapEditorSkin;

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


        eventEditor = new EventEditor(mapEditorSkin, guiViewport, editor);
        eventEditor.setVisible(false);
        editor.setEventChangeListener(new EventChangeListener() {
            @Override
            public void selectedNewEvent(EventTile newEvent, boolean created) {
                eventEditor.setVisible(true);
                eventEditor.fillData(newEvent);

                Logger.log(this, "Selected new event", newEvent);
            }

            @Override
            public void deselectedEvent(EventTile lastEvent) {
                eventEditor.setVisible(false);
                eventEditor.saveData(lastEvent);

                Logger.log(this, "Deselected event", lastEvent);
            }

            @Override
            public void deletedEvent(EventTile deleted) {
                eventEditor.setVisible(false);

                Logger.log(this, "Deleted event", deleted);
            }
        });
        stage.addActor(eventEditor);

        eventEditor.addListener(new ClickListener() {
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
        if(!editor.isEventSelected()) {
            viewport.getCamera().position.x += 200 * delta * ((OrthographicCamera) viewport.getCamera()).zoom * input.getHorizontal();
            viewport.getCamera().position.y -= 200 * delta * ((OrthographicCamera) viewport.getCamera()).zoom * input.getVertical();
        }

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
    public void resize(int width, int height) {
        viewport.update(width, height, false);
        guiViewport.update(width, height, true);
        eventEditor.setPosition(guiViewport.getWorldWidth() - eventEditor.getWidth(), 0);
    }
}
