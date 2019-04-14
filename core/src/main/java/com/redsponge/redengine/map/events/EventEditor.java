package com.redsponge.redengine.map.events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.map.MapEditor;
import com.redsponge.redengine.utils.Logger;
import com.redsponge.redengine.utils.holders.Pair;
import com.redsponge.redengine.utils.holders.Triple;

import java.util.HashMap;

public class EventEditor extends Table {

    private EventParams params;
    private TextField eventId;
    private CheckBox runOnce;

    public EventEditor(Skin skin, Viewport viewport, MapEditor mapEditor) {
        super(skin);
        setTouchable(Touchable.enabled);
        setBackground(skin.getDrawable("window_background"));
        setDebug(true);
        setSize(400, 250);
        setPosition(viewport.getWorldWidth() - getWidth(), 0);

        Label title = new Label("Edit Event", skin);
        add(title).growX().height(Value.percentHeight(0.1f, this));

        Table edited = new Table(skin);
        Label eventIdLabel = new Label("Json Id: ", skin);
        eventId = new TextField("", skin);
        edited.add(eventIdLabel, eventId);
        edited.row();
        runOnce = new CheckBox("Run Event Once", skin);
        edited.add(runOnce).colspan(2).center();

        params = new EventParams(skin);

        edited.row();
        edited.add(params).growX().colspan(2).center().padTop(10f);

        TextButton deleteEvent = new TextButton("Delete Event", skin);
        deleteEvent.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mapEditor.removeSelectedEvent();
            }
        });

        edited.row();
        edited.add(deleteEvent).colspan(2).center().padTop(10f);

        edited.pad(10f);

        ScrollPane scrollPane = new ScrollPane(edited, skin);
        scrollPane.setFadeScrollBars(false);
        row();
        add(scrollPane).growX().height(Value.percentHeight(0.9f, this));
    }

    public void fillData(EventTile event) {
        System.out.println(event);
        eventId.setText(event.getEventId());
        this.params.clearParams();

        HashMap<String, Object> params = event.getParameters();
        for(String key : params.keySet()) {
            this.params.createParameter(key, params.get(key).toString());
            Logger.log(this, "Loaded parameter", key, ":", params.get(key), "for event", event);
        }
        runOnce.setChecked(event.doesHappenOnce());
    }

    public void saveData(EventTile event) {
        Logger.log(this, "Saving data for", event);
        event.setEventId(eventId.getText());

        HashMap<String, Object> params = event.getParameters();
        params.clear();
        Array<Triple<String, String, EventParamType>> paramData = this.params.getData();

        for (Triple<String, String, EventParamType> paramPair : paramData) {
            if(!paramPair.a.trim().isEmpty()) {
                params.put(paramPair.a, paramPair.b);
                Logger.log(this, "Saved pair", paramPair, "for event", event);
            }
        }
        event.setHappenOnce(runOnce.isChecked());
    }
}
