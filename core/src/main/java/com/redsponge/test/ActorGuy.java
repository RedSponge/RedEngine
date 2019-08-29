package com.redsponge.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.redsponge.redengine.physics.IUpdated;
import com.redsponge.redengine.physics.PActor;
import com.redsponge.redengine.physics.PhysicsWorld;

public class ActorGuy extends PActor implements IUpdated {

    int y;
    public ActorGuy(PhysicsWorld worldIn) {
        super(worldIn);
        pos.set(200, 200);
        size.set(20, 20);
    }

    @Override
    public void update(float delta) {
        y -= 5;
        if(y < -100) y = -100;
        if(Gdx.input.isKeyPressed(Keys.SPACE)) {
            y = 50;
        }
        moveY(y, null);
    }
}
