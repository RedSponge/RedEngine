package com.redsponge.redengine.map.events;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.map.MapEditor;
import com.redsponge.redengine.utils.Logger;
import com.redsponge.redengine.utils.holders.Pair;

import java.util.HashMap;

public class EventEditor extends Window {

    private EventParams params;

    private TextField eventId;

    public EventEditor(Skin skin, Viewport viewport, MapEditor mapEditor) {
        super("", skin);

        setDebug(true);
        setSize(400, 250);

        setPosition(viewport.getWorldWidth() - getWidth(), 0);

        Label title = new Label("Edit Event", skin);
        title.setHeight(getHeight() * 0.1f);
        title.setPosition(getWidth() / 2, getHeight() - title.getPrefHeight() - 5, Align.center);
        addActor(title);

        Table table = new Table();
        table.setDebug(true);
        table.add(new Label("Event Json Id: ", skin));
        eventId = new TextField("", skin);
        table.add(eventId);


        table.setFillParent(true);
        table.row();
        Label parameters = new Label("Parameters", skin);
        table.add(parameters).colspan(2).center().pad(10f);
        table.row();
        params = new EventParams(skin);
        table.add(params).colspan(2).center().padTop(10f);

        ScrollPane pane = new ScrollPane(table, skin);

        pane.setWidth(getWidth());
        pane.setHeight(getHeight() * 0.9f);

        pane.setFadeScrollBars(false);

        TextButton deleteButton = new TextButton("Delete Event", skin);
        deleteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapEditor.removeSelectedEvent();
            }
        });
        table.row();
        table.add(deleteButton).padTop(10).colspan(2).align(Align.center).center();
        addActor(pane);

    }

    public void fillData(EventTile event) {
        System.out.println(event);
        eventId.setText(event.getEventId());
        this.params.clearParams();

        HashMap<String, Object> params = event.getParameters();
        for(String key : params.keySet()) {
            this.params.createParameter(key, params.get(key).toString());
            Logger.log(this, "Created parameter", key, ":", params.get(key), "for event", event);
        }
    }

    public void saveData(EventTile event) {
        event.setEventId(eventId.getText());

        HashMap<String, Object> params = event.getParameters();
        this.params.clearParams();
        Array<Pair<String, String>> paramPairs = this.params.getPairs();

        for (Pair<String, String> paramPair : paramPairs) {
            params.put(paramPair.a, paramPair.b);
            Logger.log(this, "Put pair", paramPair, "for event", event);
        }
    }
}
