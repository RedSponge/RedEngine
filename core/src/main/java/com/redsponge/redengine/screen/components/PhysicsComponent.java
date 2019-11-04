package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.redsponge.redengine.physics.PActor;
import com.redsponge.redengine.physics.PBodyType;
import com.redsponge.redengine.physics.PEntity;

import java.util.function.Consumer;

/**
 *
 */
public class PhysicsComponent implements Component {

    private PEntity body;
    private PBodyType type;
    private Consumer<Entity> onCollide;

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

    public Consumer<Entity> getOnCollide() {
        return onCollide;
    }

    public PhysicsComponent setOnCollide(Consumer<Entity> onCollide) {
        this.onCollide = onCollide;
        return this;
    }
}
