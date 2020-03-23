package com.redsponge.redengine.physics;

@FunctionalInterface
public interface RidingCheck {
    boolean isRiding(PSolid solid);
}
