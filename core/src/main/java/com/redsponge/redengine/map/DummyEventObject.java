package com.redsponge.redengine.map;

public class DummyEventObject {

    public Object testField;

    public DummyEventObject() {
        testField = -5;
    }

    public void mySampleMethod(int a, float b, String c) {
        System.out.println(c + (a + b));
    }
}