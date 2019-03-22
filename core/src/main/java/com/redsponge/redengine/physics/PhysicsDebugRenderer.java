package com.redsponge.redengine.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * A debug renderer for {@link PhysicsWorld}
 */
public class PhysicsDebugRenderer implements Disposable {

    private Viewport viewport;
    private ShapeRenderer debugRenderer;

    private static final Color ACTOR_COLOR = Color.BLUE, SOLID_COLOR = Color.GREEN;

    public PhysicsDebugRenderer() {
        this.debugRenderer = new ShapeRenderer();
    }

    /**
     * Renders a world: solids in green and actors in blue
     * @param world The world to render
     * @param projectionMatrix The projection matrix to render through
     */
    public void render(PhysicsWorld world, Matrix4 projectionMatrix) {
        debugRenderer.setProjectionMatrix(projectionMatrix);

        debugRenderer.begin(ShapeType.Line);
        debugRenderer.setColor(ACTOR_COLOR);
        for(PActor actor : world.getActors()) {
            debugRenderer.rect(actor.pos.x, actor.pos.y, actor.size.x, actor.size.y);
        }

        debugRenderer.setColor(SOLID_COLOR);
        for(PSolid solid : world.getSolids()) {
            debugRenderer.rect(solid.pos.x, solid.pos.y, solid.size.x, solid.size.y);
        }

        debugRenderer.end();
    }


    @Override
    public void dispose() {
        debugRenderer.dispose();
    }
}
