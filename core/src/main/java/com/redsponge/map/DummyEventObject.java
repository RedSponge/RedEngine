package com.redsponge.map;

public class DummyEventObject {

    public Object testField;

    public DummyEventObject() {
        testField = -5;
    }

    public void mySampleMethod(int a, String s, float b, String c) {
        System.out.println(c + (a + b));
    }
}
