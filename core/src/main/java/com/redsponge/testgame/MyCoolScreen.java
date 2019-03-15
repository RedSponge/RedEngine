package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.input.InputTranslator;
import com.redsponge.redengine.input.SimpleInputTranslator;
import com.redsponge.redengine.physics.PActor;
import com.redsponge.redengine.physics.PSolid;
import com.redsponge.redengine.physics.PhysicsDebugRenderer;
import com.redsponge.redengine.physics.PhysicsWorld;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

public class MyCoolScreen extends AbstractScreen {

    private PhysicsWorld world;
    private PhysicsDebugRenderer debugRenderer;
    private FitViewport viewport;

    private PActor myActor;
    private PSolid mySolid;

    private InputTranslator input;

    public MyCoolScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        viewport = new FitViewport(500, 500);
        world = new PhysicsWorld();
        debugRenderer = new PhysicsDebugRenderer();

        myActor = new PActor(world);
        myActor.pos.set(10, 10);
        myActor.size.set(40, 40);

        world.addActor(myActor);

        PSolid solid = new PSolid(world);
        solid.pos.set(300, 100);
        solid.size.set(30, 100);

        world.addSolid(solid);

        mySolid = new PSolid(world);
        mySolid.pos.set(0, 100);
        mySolid.size.set(20, 100);

        world.addSolid(mySolid);

        input = new SimpleInputTranslator();
    }

    @Override
    public void tick(float delta) {
        float horiz = input.getHorizontal();
        float vert = input.getVertical();

        if(horiz != 0) {
            myActor.moveX(delta * 100 * horiz, null);
        }

        if(vert != 0) {
            myActor.moveY(delta * 100 * vert, null);
        }

        mySolid.move(1, 0);
        world.update();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        debugRenderer.render(world, viewport.getCamera().combined);
    }

    @Override
    public AssetDescriptor[] getRequiredAssets() {
        return new AssetDescriptor[0];
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        debugRenderer.dispose();
    }
}
