package com.redsponge.redengine.screen.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.screen.components.AnimationComponent;
import com.redsponge.redengine.screen.components.Mappers;
import com.redsponge.redengine.screen.components.PositionComponent;
import com.redsponge.redengine.screen.components.RenderComponent;
import com.redsponge.redengine.screen.components.SizeComponent;
import com.redsponge.redengine.screen.components.TextureComponent;

public class RenderSystem extends SortedIteratingSystem {

    private FitViewport viewport;
    private SpriteBatch batch;

    public RenderSystem(int width, int height, SpriteBatch batch) {
        super(Family.all(PositionComponent.class, SizeComponent.class, RenderComponent.class).get(), ZComparator.instance);
        this.batch = batch;
        viewport = new FitViewport(width, height);
        viewport.apply(true);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = Mappers.position.get(entity);
        SizeComponent size = Mappers.size.get(entity);
        RenderComponent render = Mappers.render.get(entity);

        TextureRegion drawn = render.getRegion();
        boolean flipX = render.isFlipX();
        boolean flipY = render.isFlipY();

        if(drawn == null) return;

        drawn.flip(flipX, flipY);
        batch.draw(drawn, pos.getX(), pos.getY(), size.getX(), size.getY());
        drawn.flip(flipX, flipY);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public OrthographicCamera getCamera() {
        return (OrthographicCamera) viewport.getCamera();
    }
}
