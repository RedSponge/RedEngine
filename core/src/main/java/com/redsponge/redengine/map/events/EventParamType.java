package com.redsponge.redengine.map.events;

public enum EventParamType {
    INTEGER("Int"),
    FLOAT("Float"),
    STRING("Text");

    private String name;

    EventParamType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
