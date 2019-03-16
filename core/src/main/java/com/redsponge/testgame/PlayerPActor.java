package com.redsponge.testgame;

import com.badlogic.gdx.math.Vector2;
import com.redsponge.redengine.input.InputTranslator;
import com.redsponge.redengine.input.SimpleInputTranslator;
import com.redsponge.redengine.physics.IUpdated;
import com.redsponge.redengine.physics.PActor;
import com.redsponge.redengine.physics.PSolid;
import com.redsponge.redengine.physics.PhysicsWorld;

public class PlayerPActor extends PActor implements IUpdated {

    private Vector2 vel;
    private InputTranslator input;

    private boolean onGround;

    public PlayerPActor(PhysicsWorld worldIn) {
        super(worldIn);
        this.vel = new Vector2();
        this.pos.set(100, 100);
        this.size.set(40, 40);
        this.input = new SimpleInputTranslator();
    }

    @Override
    public void update(float delta) {
        vel.add(0, -10 * delta); // Apply Gravity

        if(input.isJustJumping() && onGround) {
            onGround = false;
            vel.y = 5;
        }

        vel.x += input.getHorizontal();
        vel.x *= 0.9f;

        moveX(vel.x, () -> {vel.x = 0;});
        moveY(vel.y, null);

        PSolid floor = collideFirst(pos.copy().add(0, -1));

        if(floor != null) {
            onGround = true;
            vel.y = 0;
            if(floor instanceof MovingPSolid) {
                if(!((MovingPSolid) floor).isActive()) {
                    ((MovingPSolid) floor).activate();
                }
            }
        } else {
            onGround = false;
        }
    }
}
