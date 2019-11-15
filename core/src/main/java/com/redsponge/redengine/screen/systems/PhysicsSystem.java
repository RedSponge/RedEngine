package com.redsponge.redengine.screen.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.redsponge.redengine.physics.PActor;
import com.redsponge.redengine.physics.PEntity;
import com.redsponge.redengine.physics.PSolid;
import com.redsponge.redengine.physics.PhysicsUtils;
import com.redsponge.redengine.physics.PhysicsWorld;
import com.redsponge.redengine.screen.components.Mappers;
import com.redsponge.redengine.screen.components.PhysicsComponent;
import com.redsponge.redengine.screen.components.PositionComponent;
import com.redsponge.redengine.screen.components.SizeComponent;
import com.redsponge.redengine.screen.components.VelocityComponent;
import com.redsponge.redengine.utils.Logger;

public class PhysicsSystem extends IteratingSystem implements EntityListener {

    private PhysicsWorld pWorld;

    public PhysicsSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
        pWorld = new PhysicsWorld();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Logger.log(this, "Entity:", entity);
        processBeenSets(entity);
        PositionComponent pos = Mappers.position.get(entity);
        VelocityComponent vel = Mappers.velocity.get(entity);
        PhysicsComponent physics = Mappers.physics.get(entity);

        if(physics != null) {
            PhysicsUtils.moveEntity(physics.getBody(), vel.getX(), vel.getY(), physics.getOnCollideX(), physics.getOnCollideY());
            //TODO: Bug is that for some reason physics.getBody is null
            pos.set(physics.getBody().pos.x, physics.getBody().pos.y);
        } else {
            pos.setX(pos.getX() + vel.getX() * deltaTime);
        }
    }

    private void processBeenSets(Entity entity) {
        PhysicsComponent physics = Mappers.physics.get(entity);
        if(physics == null) return;

        PositionComponent pos = Mappers.position.get(entity);
        SizeComponent size = Mappers.size.get(entity);

        if(pos.isBeenSet()) {
            pos.setBeenSet(false);
            Logger.log(this, entity, physics.getBody());
//            physics.getBody().pos.set((int) pos.getX(), (int) pos.getY());
        }
        if (size.isBeenSet()) {
            size.setBeenSet(false);
//            physics.getBody().size.set(size.getScaledX(), size.getScaledY());
        }
    }

    public PhysicsSystem(Family family, PhysicsWorld pWorld) {
        super(family);
        this.pWorld = pWorld;
    }

    @Override
    public void entityAdded(Entity entity) {
        PositionComponent pos = Mappers.position.get(entity);
        SizeComponent size = Mappers.size.get(entity);
        PhysicsComponent physics = Mappers.physics.get(entity);

        if(physics != null) {

            PEntity pEntity;

            switch (physics.getType()) {
                case ACTOR:
                    pEntity = new PActor(pWorld);
                    break;
                case SOLID:
                    pEntity = new PSolid(pWorld);
                    break;
                default:
                    Logger.error(this, "Error! Invalid body type when trying to add to body", physics.getType());
                    return;
            }
            pEntity.setConnectedEntity(entity);
            pEntity.pos.set((int) pos.getX(), (int) pos.getY());
            pEntity.size.set(size.getX(), size.getY());

            switch (physics.getType()) {
                case ACTOR:
                    pWorld.addActor((PActor) pEntity);
                    break;
                case SOLID:
                    pWorld.addSolid((PSolid) pEntity);
                    break;
                default:
                    Logger.error(this, "Error! Invalid body type when trying to add to body", physics.getType());
            }
            Logger.log(this, "Finished creating new body:", pEntity);
            physics.setBody(pEntity);
            Logger.log(this, "physics.getBody() =", physics.getBody());
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
        PhysicsComponent physics = Mappers.physics.get(entity);
        if(physics == null) return;
        physics.getBody().remove();
    }

    public PhysicsWorld getPhysicsWorld() {
        return pWorld;
    }
}
