package com.redsponge.redengine.physics;

@FunctionalInterface
public interface RidingCheck {
    boolean isRiding(PActor self, PSolid solid);
}
