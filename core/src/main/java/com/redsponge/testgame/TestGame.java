package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.redsponge.map.events.Event;
import com.redsponge.map.events.EventDataHolder;
import com.redsponge.redengine.EngineGame;

public class TestGame extends EngineGame {

    @Override
    public void init() {
        setScreen(new Screen2(ga));

        if(true) return;
        try {
            OrthographicCamera cam = new OrthographicCamera();
            cam.zoom = 0.5f;
            EventDataHolder edh = new EventDataHolder(cam);
            Event e = new Event(Gdx.files.internal("events/run_method_example.json"), edh);

            e.setValue("mySuperValue", 2);
            e.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
