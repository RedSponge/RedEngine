package com.redsponge.redengine.map.events;

import com.badlogic.gdx.files.FileHandle;
import com.redsponge.redengine.utils.holders.Pair;

import java.util.HashMap;

public class Event {

    private String json;
    private HashMap<String, Object> params;

    @SafeVarargs
    public Event(FileHandle json, Pair<String, Object>... objects) {
        this.params = new HashMap<String, Object>();

        for(Pair<String, Object> p : objects) {
            params.put(p.a, p.b);
        }
        this.json = json.readString();
    }

    public void setValue(String name, Object value) {
        params.put(name, value);
    }
}
