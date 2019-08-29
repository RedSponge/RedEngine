package com.redsponge.redengine.physics;

import com.badlogic.gdx.utils.Array;
import com.redsponge.redengine.utils.MathUtilities;

/**
 * A solid in a {@link PhysicsWorld}. cannot interact with other solids
 */
public class PSolid extends PEntity {

    private float remainderX, remainderY;
    private boolean collidable;

    public PSolid(PhysicsWorld worldIn) {
        super(worldIn);
        collidable = true;
    }

    /**
     * Moves a solid, pushing and moving all riding / in the way actors
     * @param x The x amount to move
     * @param y The y amount to move
     */
    public final void move(float x, float y) {
        remainderX += x;
        remainderY += y;

        int moveX = Math.round(remainderX);
        int moveY = Math.round(remainderY);

        if(moveX != 0 || moveY != 0) {
            Array<PActor> ridingActors = getAllRidingActors();
            collidable = false;

            if(moveX != 0) {
                remainderX -= moveX;
                pos.x += moveX;

                if (moveX > 0) {
                    for (PActor actor : worldIn.getActors()) {
                        if (MathUtilities.rectanglesIntersect(this.pos, this.size, actor.pos, actor.size)) {
                            actor.moveX(this.pos.x + this.size.x - actor.pos.x, actor::squish); // Push
                        } else if (ridingActors.contains(actor, true)) {
                            actor.moveX(moveX, null); // Carry
                        }
                    }
                } else {
                    for (PActor actor : worldIn.getActors()) {
                        if (MathUtilities.rectanglesIntersect(this.pos, this.size, actor.pos, actor.size)) {
                            actor.moveX(this.pos.x - (actor.pos.x + actor.size.x), actor::squish); // Push
                        } else if (ridingActors.contains(actor, true)) {
                            actor.moveX(moveX, null); // Carry
                        }
                    }
                }
            }

            if(moveY != 0) {
                remainderY -= moveY;
                pos.y +=  moveY;

                if (moveY > 0) {
                    for (PActor actor : worldIn.getActors()) {
                        if (MathUtilities.rectanglesIntersect(this.pos, this.size, actor.pos, actor.size)) {
                            actor.moveY(this.pos.y + this.size.y - actor.pos.y, actor::squish); // Push
                        } else if (ridingActors.contains(actor, true)) {
                            actor.moveY(moveY, null); // Carry
                        }
                    }
                } else {
                    for (PActor actor : worldIn.getActors()) {
                        if (MathUtilities.rectanglesIntersect(this.pos, this.size, actor.pos, actor.size)) {
                            actor.moveY(this.pos.y - (actor.pos.y + actor.size.y), actor::squish); // Push
                        } else if (ridingActors.contains(actor, true)) {
                            actor.moveY(moveY, null); // Carry
                        }
                    }
                }
            }

            collidable = true;
        }
    }

    /**
     * Checks actors with {@link PActor#isRiding(PSolid)} and returns all the riding actors
     * @return An array containing all actors which are riding this solid
     */
    private Array<PActor> getAllRidingActors() {
        Array<PActor> out = new Array<PActor>();
        for(PActor actor : worldIn.getActors()) {
            if(actor.isRiding(this)) {
                out.add(actor);
            }
        }
        return out;
    }

    public boolean isCollidable() {
        return collidable;
    }
}
