package com.redsponge.redengine.physics;

@FunctionalInterface
public interface RidingCheck {

    RidingCheck DEFAULT = (self, solid) -> solid.pos.y + solid.size.y == self.pos.y;

    boolean isRiding(PActor self, PSolid solid);
}
