package com.redsponge.test;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.collider.Collider;
import com.redsponge.redengine.collider.ColliderOpts;
import com.redsponge.redengine.collider.HBCircle;
import com.redsponge.redengine.collider.HitBox;
import com.redsponge.redengine.collider.InteractTester;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;

import static java.lang.Float.POSITIVE_INFINITY;

public class TestPhysicsScreen extends AbstractScreen {

    private Collider collider;
    private HBCircle circle;
    private HBCircle circle2;

    private FitViewport viewport;

    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;

    public TestPhysicsScreen(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void show() {
        ColliderOpts opts = new ColliderOpts();
        opts.maxForesightTime = 10;
        opts.interactTester = new InteractTester() {
            @Override
            public boolean canInteract(HitBox a, HitBox b) {
                return true;
            }

            @Override
            public int[] getInteractGroups(HitBox hitBox) {
                return new int[0];
            }
        };
        opts.cellWidth = 10;
        opts.separateBuffer = 1;

        collider = new Collider(opts);
        circle = collider.makeCircle();
        circle.setPos(10, 10);
        circle.setVel(10, 5);
        circle.setDiam(10);
        circle.commit(POSITIVE_INFINITY);

        circle2 = collider.makeCircle();
        circle2.setPos(200, 100);
        circle2.setVel(-10, -5);
        circle2.setDiam(20);
        circle2.commit(POSITIVE_INFINITY);

        viewport = new FitViewport(WIDTH, HEIGHT);
    }

    private float time;

    @Override
    public void tick(float delta) {
        time += delta * 2;
        collider.stepToTime(time, true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.circle((float) circle.getX(), (float) circle.getY(), (float) circle.getDiam() / 2);
        shapeRenderer.circle((float) circle2.getX(), (float) circle2.getY(), (float) circle2.getDiam() / 2);

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public Class<? extends AssetSpecifier> getAssetSpecsType() {
        return null;
    }
}
