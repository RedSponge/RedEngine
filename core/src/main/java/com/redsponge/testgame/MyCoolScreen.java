package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.input.InputTranslator;
import com.redsponge.redengine.input.SimpleInputTranslator;
import com.redsponge.redengine.physics.PSolid;
import com.redsponge.redengine.physics.PhysicsDebugRenderer;
import com.redsponge.redengine.physics.PhysicsWorld;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

public class MyCoolScreen extends AbstractScreen {

    private PhysicsWorld world;
    private PhysicsDebugRenderer debugRenderer;
    private FitViewport viewport;

    private PlayerPActor myActor;
    private PSolid mySolid;

    private InputTranslator input;

    public MyCoolScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        viewport = new FitViewport(512, 512);
        world = new PhysicsWorld();
        debugRenderer = new PhysicsDebugRenderer();

        myActor = new PlayerPActor(world);

        world.addActor(myActor);

        PSolid floor = new PSolid(world);
        floor.pos.set(0, 0);
        floor.size.set((int) viewport.getWorldWidth(), 30);

        world.addSolid(floor);

        mySolid = new MovingPSolid(world);
        mySolid.pos.set(0, 50);
        mySolid.size.set(100, 20);

        world.addSolid(mySolid);

        input = new SimpleInputTranslator();
    }

    @Override
    public void tick(float delta) {
        float horiz = input.getHorizontal();
        float vert = input.getVertical();


        world.update(delta);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.getCamera().position.set(new Vector3(myActor.pos.x, myActor.pos.y, 0));
        viewport.apply();

        debugRenderer.render(world, viewport.getCamera().combined);
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
