package com.redsponge.redengine.physics;

import com.redsponge.redengine.utils.IntVector2;
import com.redsponge.redengine.utils.Logger;
import com.redsponge.redengine.utils.MathUtilities;

/**
 * An actor in the world, must never overlap a solid
 */
public class PActor extends PEntity {

    protected boolean collidable;
    private float remainderX, remainderY;
    private RidingCheck ridingCheck;

    public static final RidingCheck defaultRidingCheck = ((self, solid) -> solid.pos.y + solid.size.y == self.pos.y);

    public PActor(PhysicsWorld worldIn) {
        super(worldIn);
    }

    /**
     * Called to move the actor on the x axis
     * @param x The x amount to move
     * @param onCollide Called when the actor collides with a solid
     */
    public final void moveX(float x, OnCollide onCollide) {
        remainderX += x;
        int move = (int) remainderX;
        if(move != 0) {
            remainderX -= move;
            int sign = (int) Math.signum(move);

            while(move != 0) {
                pos.add(sign, 0);
                PSolid col = getFirstCollision(pos);
                pos.add(-sign, 0);

                if(col == null) {
                    pos.x += sign;
                    move -= sign;
                } else {
                    // Collided With A Solid!
//                    Logger.log(this, "Collision!");
                    if(onCollide != null) {
                        onCollide.onCollide(col);
                    }
                    move = 0; // Break out of the while loop
                }
            }
        }
    }

    public RidingCheck getRidingCheck() {
        return ridingCheck;
    }

    public void setRidingCheck(RidingCheck ridingCheck) {
        this.ridingCheck = ridingCheck;
    }

    /**
     * Called to move the actor on the y axis
     * @param y The y amount to move
     * @param onCollide Called when the actor collides with a solid
     */
    public final void moveY(float y, OnCollide onCollide) {
        remainderY += y;
        int move = Math.round(remainderY);

        if(move != 0) {
            remainderY -= move;
            int sign = (int) Math.signum(move);

            while(move != 0) {
                pos.add(0, sign);
                PSolid col = getFirstCollision(pos);
                pos.add(0, -sign);
                if(col == null) {
                    pos.y += sign;
                    move -= sign;
                } else {
                    // Collided With A Solid!
//                    Logger.log(this, "Collision!");
                    if(onCollide != null) {
                        onCollide.onCollide(col);
                    }
                    move = 0; // Break out of the while loop
                }
            }
        }
    }

    /**
     * Checks if the actor collided with a solid
     * @param pos The checking position
     * @return Has the actor collided with a solid?
     */
    protected boolean collideAt(IntVector2 pos) {
        for(PSolid solid : worldIn.getSolids()) {
            if(solid.isCollidable() && MathUtilities.rectanglesIntersect(pos, this.size, solid.pos, solid.size)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the first thing the actor will collide with in a given position
     * @param pos The checking position
     * @return The first solid the actor will collide with when in this position. null if none
     */
    public PSolid getFirstCollision(IntVector2 pos) {
        for(PSolid solid : worldIn.getSolids()) {
            if(solid.isCollidable() && MathUtilities.rectanglesIntersect(pos, this.size, solid.pos, solid.size)) {
                return solid;
            }
        }
        return null;
    }

    /**
     * Checks if the actor is riding a solid - Defaults to if the actor is standing on the solid
     * @param solid The solid to check
     * @return Is the actor riding the solid?
     */
    public boolean isRiding(PSolid solid) {
        if(ridingCheck != null) {
            return ridingCheck.isRiding(this, solid);
        }
        return defaultRidingCheck.isRiding(this, solid);
    }

    /**
     * Called when the actor is squished between two {@link PSolid}s
     * Defaults to destroying the actor
     */
    public void squish(PSolid solid) {
        Logger.log(this, "Got Squished!");
        this.remove();
    }

}
