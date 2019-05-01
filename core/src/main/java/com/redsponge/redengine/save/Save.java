package com.redsponge.redengine.save;

import java.util.ArrayList;
import java.util.List;

public class Save {

    private List<Player> players;
    private Achievements achievements;

    public Save() {
        players = new ArrayList<Player>();
        achievements = new Achievements();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Achievements getAchievements() {
        return achievements;
    }
}
