package com.redsponge.redengine.physics;

import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class PhysicsWorld {

    private DelayedRemovalArray<PActor> actors;
    private DelayedRemovalArray<PSolid> solids;

    public PhysicsWorld() {
        this.actors = new DelayedRemovalArray<PActor>();
        this.solids = new DelayedRemovalArray<PSolid>();
    }

    /**
     * Adds an actor to the world
     * @param actor The actor to add
     */
    public void addActor(PActor actor) {
        actors.add(actor);
    }

    /**
     * Adds a solid to the world
     * @param solid The solid to add
     */
    public void addSolid(PSolid solid) {
        solids.add(solid);
    }

    /**
     * Removes all things that should be removed
     */
    public void update(float delta) {
        for(PActor actor : actors) {
            if(actor instanceof IUpdated) {
                ((IUpdated) actor).update(delta);
            }
            if(actor.isRemoved()) {
                actors.removeValue(actor, true);
            }
        }

        for(PSolid solid : solids) {
            if(solid instanceof IUpdated) {
                ((IUpdated) solid).update(delta);
            }
            if(solid.isRemoved()) {
                solids.removeValue(solid, true);
            }
        }
    }


    public DelayedRemovalArray<PSolid> getSolids() {
        return solids;
    }

    public DelayedRemovalArray<PActor> getActors() {
        return actors;
    }
}
