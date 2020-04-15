package com.redsponge.redengine.physics;

@FunctionalInterface
public interface SquishListener {

    SquishListener DEFAULT = (self, solid) -> self.remove();

    void squish(PActor self, PSolid solid);
}
