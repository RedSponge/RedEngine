package com.redsponge.redengine.physics;

import com.redsponge.redengine.utils.GeneralUtils;
import com.redsponge.redengine.utils.IntVector2;
import com.redsponge.redengine.utils.Logger;
import com.redsponge.redengine.utils.MathUtilities;

/**
 * An actor in the world, must never overlap a solid
 */
public class PActor extends PEntity {
    private float remainderX, remainderY;

    public PActor(PhysicsWorld worldIn) {
        super(worldIn);
    }

    /**
     * Called to move the actor on the x axis
     * @param x The x amount to move
     * @param onCollide Called when the actor collides with a solid
     */
    public final void moveX(float x, Runnable onCollide) {
        remainderX += x;
        int move = (int) remainderX;
        if(move != 0) {
            remainderX -= move;
            int sign = (int) Math.signum(move);

            while(move != 0) {
                if(!collideAt(pos.copy().add(sign, 0))) {
                    pos.x += sign;
                    move -= sign;
                } else {
                    // Collided With A Solid!
                    Logger.log(this, "Collision!");
                    if(onCollide != null) {
                        onCollide.run();
                    }
                    move = 0; // Break out of the while loop
                }
            }
        }
    }

    /**
     * Called to move the actor on the y axis
     * @param y The y amount to move
     * @param onCollide Called when the actor collides with a solid
     */
    public final void moveY(float y, Runnable onCollide) {
        remainderY += y;
        int move = Math.round(remainderY);

        if(move != 0) {
            remainderY -= move;
            int sign = (int) Math.signum(move);

            while(move != 0) {
                if(!collideAt(pos.copy().add(0, sign))) {
                    pos.y += sign;
                    move -= sign;
                } else {
                    // Collided With A Solid!
                    Logger.log(this, "Collision!");
                    if(onCollide != null) {
                        onCollide.run();
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
    protected PSolid collideFirst(IntVector2 pos) {
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
        return solid.pos.y + solid.size.y == this.pos.y;
    }

    /**
     * Called when the actor is squished between two {@link PSolid}s
     * Defaults to destroying the actor
     */
    public void squish() {
        Logger.log(this, "Got Squished!");
        this.remove();
    }

}
