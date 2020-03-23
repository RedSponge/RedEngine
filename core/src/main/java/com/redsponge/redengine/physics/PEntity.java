package com.redsponge.redengine.physics;

import com.badlogic.ashley.core.Entity;
import com.redsponge.redengine.utils.IntVector2;

/**
 * An object in a {@link PhysicsWorld}. has size and position.
 */
public class PEntity {

    public IntVector2 pos, size;
    protected PhysicsWorld worldIn;
    private Entity connectedEntity;
    private boolean wasMoved;

    private String physicsBodyTag = "";

    /**
     * If true, the world object will be removed from the {@link PhysicsWorld} on the next tick
     */
    private boolean removed;

    public PEntity(PhysicsWorld worldIn) {
        this.worldIn = worldIn;
        this.pos = new IntVector2();
        this.size = new IntVector2();
    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public Entity getConnectedEntity() {
        return connectedEntity;
    }

    public PEntity setConnectedEntity(Entity connectedEntity) {
        this.connectedEntity = connectedEntity;
        return this;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
    }

    public boolean getWasMoved() {
        return wasMoved;
    }

    public String getPhysicsBodyTag() {
        return physicsBodyTag;
    }

    public void setPhysicsBodyTag(String physicsBodyTag) {
        this.physicsBodyTag = physicsBodyTag;
    }
}
