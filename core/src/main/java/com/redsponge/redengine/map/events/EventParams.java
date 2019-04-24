package com.redsponge.redengine.map.events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.redsponge.redengine.utils.holders.Triple;

public class EventParams extends Table {

    private Array<Triple<TextField, TextField, SelectBox<EventParamType>>> params;
    private TextButton addParameter;

    public EventParams(Skin skin) {
        super(skin);
        setDebug(true);
        params = new Array<Triple<TextField, TextField, SelectBox<EventParamType>>>();

        addParameter = new TextButton("Add New Parameter", skin);
        addParameter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                createParameter("", "", EventParamType.STRING);
            }
        });

        add(addParameter).colspan(2).center();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void createParameter(String name, String value, EventParamType type) {
        row();
        Label nameL = new Label("Name", getSkin());
        Label valueL= new Label("Value", getSkin());
        Label typeL= new Label("Type", getSkin());
        add(nameL).padTop(10);
        add(valueL).padTop(10);
        add(typeL).padTop(10);
        row();
        TextField nameF = new TextField(name, getSkin());
        TextField valueF = new TextField(value, getSkin());
        SelectBox<EventParamType> typeSelectBox = new SelectBox<EventParamType>(getSkin());
        typeSelectBox.setItems(EventParamType.values());
        typeSelectBox.setSelected(type);

        add(nameF).padTop(10).width(Value.percentWidth(0.4f, this));
        add(valueF).padTop(10).width(Value.percentWidth(0.4f, this));
        add(typeSelectBox).padTop(10).width(Value.percentWidth(0.2f, this));
        row();

        params.add(new Triple<TextField, TextField, SelectBox<EventParamType>>(nameF, valueF, typeSelectBox));
    }

    public void clearParams() {
        this.clearChildren();
        add(addParameter).colspan(2).center();
        params.clear();
    }

    public Array<Triple<String, String, EventParamType>> getData() {
        Array<Triple<String, String, EventParamType>> out = new Array<Triple<String, String, EventParamType>>();

        for(Triple<TextField, TextField, SelectBox<EventParamType>> param : this.params) {
            Triple<String, String, EventParamType> added = new Triple<String, String, EventParamType>(param.a.getText(), param.b.getText(), (EventParamType) param.c.getSelected());
            out.add(added);
        }
        return out;
    }


    public TextButton getAddParameter() {
        return addParameter;
    }
}
