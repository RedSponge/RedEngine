package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonValue;
import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.save.SaveFile;

public class TestGame extends EngineGame {

    @Override
    public void init() {
        try {
            SaveFile mySaveFile = new SaveFile("hello.sav");
            mySaveFile.getRoot().addChild("name", new JsonValue("howdy"));
            mySaveFile.saveToFile();
            setScreen(new Screen2(ga));
        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }
}
