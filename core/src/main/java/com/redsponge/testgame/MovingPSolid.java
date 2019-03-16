package com.redsponge.testgame;

import com.redsponge.redengine.physics.IUpdated;
import com.redsponge.redengine.physics.PSolid;
import com.redsponge.redengine.physics.PhysicsWorld;

public class MovingPSolid extends PSolid implements IUpdated {

    private boolean moving;

    public MovingPSolid(PhysicsWorld worldIn) {
        super(worldIn);
    }

    public void activate() {
        moving = true;
    }

    @Override
    public void update(float delta) {
        if(moving) {
            this.move(50 * delta,0);
        }
    }

    public boolean isActive() {
        return moving;
    }
}
