package com.redsponge.redengine.save;

import com.badlogic.gdx.utils.ObjectMap;

public class Player {

    private String name;
    private int age;
    private ObjectMap<String, String> stats;

    public Player() {
        this(null, -1);
    }

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.stats = new ObjectMap<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ObjectMap<String, String> getStats() {
        return stats;
    }

    public void setStats(ObjectMap<String, String> stats) {
        this.stats = stats;
    }

    public void setStat(String statName, String value) {
        stats.put(statName, value);
    }

    public String getStat(String statName) {
        return stats.get(statName);
    }
}
