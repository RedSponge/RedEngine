package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.redsponge.redengine.EngineGame;
import com.redsponge.redengine.save.Player;
import com.redsponge.redengine.save.Save;
import com.redsponge.redengine.save.SaveFile;
import com.redsponge.redengine.screen.SplashScreenScreen;

public class TestGame extends EngineGame {

    @Override
    public void init() {
        try {
//            Save save = new Save();
//            Player special = new Player("Special", 10);
//            save.getPlayers().add(new Player("Food", 20));
//            save.getPlayers().add(new Player("Meow", 20));
//            save.getPlayers().add(special);
//
//            special.setStat("dumb", "over 9k");
//
//            save.getAchievements().setBeingDumb(true);

            SaveFile<Save> mySaveFile = new SaveFile<Save>("hello.sav");
            Save save = mySaveFile.get(Save.class);

            System.out.println(save.getAchievements().isBeingDumb());
            System.out.println(save.getAchievements().isNotBeingDumb());

            for (Player player : save.getPlayers()) {
                System.out.println(player.getName() + " " + player.getAge() + " " + player.getStats());
            }
//            mySaveFile.saveToFile(save);

//            System.out.println(save.getPlayers().get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

        setScreen(new SplashScreenScreen(ga));
    }
}
