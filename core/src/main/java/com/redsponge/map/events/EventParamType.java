package com.redsponge.map.events;

public enum EventParamType {
    INTEGER("Int", Integer.class),
    FLOAT("Float", Float.class),
    STRING("Text", String.class);

    private String name;
    private Class<?> representingClass;
    EventParamType(String name, Class<?> representingClass) {
        this.name = name;
        this.representingClass = representingClass;
    }

    public static EventParamType getForClass(Class<?> clazz) {
        for(EventParamType type : values()) {
            if(clazz == type.representingClass) {
                return type;
            }
        }
        return STRING;
    }

    @Override
    public String toString() {
        return name;
    }
}
