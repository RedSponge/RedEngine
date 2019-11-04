package com.redsponge.redengine.screen.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.redsponge.redengine.physics.PhysicsWorld;
import com.redsponge.redengine.screen.components.Mappers;
import com.redsponge.redengine.screen.components.PositionComponent;
import com.redsponge.redengine.screen.components.SizeComponent;
import com.redsponge.redengine.screen.components.VelocityComponent;

public class PhysicsSystem extends IteratingSystem implements EntityListener {

    private PhysicsWorld pWorld;

    public PhysicsSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = Mappers.position.get(entity);
        VelocityComponent vel = Mappers.velocity.get(entity);

        pos.setX(pos.getX() + vel.getX() * deltaTime);
    }

    public PhysicsSystem(Family family, PhysicsWorld pWorld) {
        super(family);
        this.pWorld = pWorld;
    }

    @Override
    public void entityAdded(Entity entity) {
        PositionComponent pos;
    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}
