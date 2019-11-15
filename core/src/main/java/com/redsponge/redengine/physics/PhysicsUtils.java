package com.redsponge.redengine.physics;

public class PhysicsUtils {

    public static void moveEntity(PEntity entity, float x, float y, OnCollide cx, OnCollide cy)
    {
        if(entity instanceof PSolid) {
            ((PSolid) entity).move(x, y);
        } else if(entity instanceof PActor) {
            ((PActor) entity).moveX(x, cx);
            ((PActor) entity).moveY(y, cy);
        }
    }

}
