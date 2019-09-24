package com.redsponge.redengine.physics.newp;

import com.badlogic.gdx.physics.box2d.Shape;
import com.redsponge.redengine.screen.entity.ScreenEntity;
import com.redsponge.redengine.screen.entity.ScreenEntityAttachment;

public class PhysicsCollider extends ScreenEntityAttachment {

    private PhysicsSystem sys;
    private Shape shape;

    public PhysicsCollider(ScreenEntity entity) {
        super(entity);
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void moveX(int x) {

    }


}
