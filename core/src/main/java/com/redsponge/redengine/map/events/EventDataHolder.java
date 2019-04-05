package com.redsponge.redengine.map.events;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.redsponge.redengine.map.DummyEventObject;

public class EventDataHolder {

    private OrthographicCamera camera;
    private DummyEventObject dev;
    public EventDataHolder(OrthographicCamera camera) {
        this.camera = camera;
        this.dev = new DummyEventObject();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public DummyEventObject getDummy() {
        return dev;
    }
}
