package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.save.SaveFile;
import com.redsponge.redengine.screen.SplashScreenScreen;

public class TestGame extends EngineGame {

    @Override
    public void init() {
        try {
            SaveFile mySaveFile = new SaveFile("hello.sav");
            mySaveFile.setValue("players.eran.foods.favorite", "Sushi");
            mySaveFile.setValue("players.eran.name", "Eran");
            mySaveFile.setValue("players.eran.age", 14);

            mySaveFile.saveToFile();

            mySaveFile.deleteValue("players.eran.name");
            mySaveFile.saveToFile();

            mySaveFile.deleteValue("players.eran.age");

//            mySaveFile.deleteValue("players.eran.age");
//            mySaveFile.deleteValue("players.eran.name");
            mySaveFile.saveToFile();
        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

        setScreen(new SplashScreenScreen(ga));
    }
}
