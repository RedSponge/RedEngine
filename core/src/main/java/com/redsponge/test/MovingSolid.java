package com.redsponge.test;

import com.redsponge.redengine.physics.IUpdated;
import com.redsponge.redengine.physics.PSolid;
import com.redsponge.redengine.physics.PhysicsWorld;

public class MovingSolid extends PSolid implements IUpdated {

    private float timeAlive;

    public MovingSolid(PhysicsWorld worldIn) {
        super(worldIn);
        pos.set(150, 100);
        size.set(100, 20);
    }

    @Override
    public void update(float delta) {
        timeAlive += delta;
        move(0, (float) (Math.sin(timeAlive * 10) * 10));
    }
}
