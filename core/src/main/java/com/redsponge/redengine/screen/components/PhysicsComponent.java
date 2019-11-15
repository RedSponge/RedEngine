package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.redsponge.redengine.physics.OnCollide;
import com.redsponge.redengine.physics.PBodyType;
import com.redsponge.redengine.physics.PEntity;

public class PhysicsComponent implements Component {

    private PEntity body;
    private PBodyType type;
    private OnCollide onCollideX;
    private OnCollide onCollideY;

    public PhysicsComponent() {
        this(PBodyType.ACTOR);
    }

    public PhysicsComponent(PBodyType type) {
        this.type = type;
    }

    public PEntity getBody() {
        return body;
    }

    public PhysicsComponent setBody(PEntity body) {
        this.body = body;
        return this;
    }

    public PBodyType getType() {
        return type;
    }

    public OnCollide getOnCollideX() {
        return onCollideX;
    }

    public PhysicsComponent setOnCollideX(OnCollide onCollideX) {
        this.onCollideX = onCollideX;
        return this;
    }

    public OnCollide getOnCollideY() {
        return onCollideY;
    }

    public PhysicsComponent setOnCollideY(OnCollide onCollideY) {
        this.onCollideY = onCollideY;
        return this;
    }
}
