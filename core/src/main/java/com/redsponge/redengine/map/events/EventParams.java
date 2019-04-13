package com.redsponge.redengine.map.events;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.redsponge.redengine.utils.holders.Pair;

public class EventParams extends Table {

    private Array<Pair<TextField, TextField>> params;
    private TextButton addParameter;

    public EventParams(Skin skin) {
        super(skin);
        setDebug(true);
        params = new Array<Pair<TextField, TextField>>();

        addParameter = new TextButton("Add New Parameter", skin);
        addParameter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                createParameter("", "");
            }
        });

        add(addParameter).colspan(2).center();
    }

    public void createParameter(String name, String value) {
        row();
        Label nameL = new Label("Name", getSkin());
        Label valueL= new Label("Value", getSkin());
        add(nameL).padTop(10);
        add(valueL).padTop(10);
        row();
        TextField nameF = new TextField(name, getSkin());
        TextField valueF = new TextField(value, getSkin());
        add(nameF).padTop(10);
        add(valueF).padTop(10);
        row();

        params.add(new Pair<TextField, TextField>(nameF, valueF));
    }

    public void clearParams() {
        this.clearChildren();
        add(addParameter).colspan(2).center();
        params.clear();
    }

    public Array<Pair<String, String>> getPairs() {
        Array<Pair<String, String>> out = new Array<Pair<String, String>>();

        for(Pair<TextField, TextField> param : this.params) {
            Pair<String, String> added = new Pair<String, String>(param.a.getText(), param.b.getText());
            out.add(added);
        }
        return out;
    }


    public TextButton getAddParameter() {
        return addParameter;
    }
}
