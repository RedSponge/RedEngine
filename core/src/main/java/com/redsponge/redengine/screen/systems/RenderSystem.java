package com.redsponge.redengine.screen.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.screen.components.Mappers;
import com.redsponge.redengine.screen.components.PositionComponent;
import com.redsponge.redengine.screen.components.RenderComponent;
import com.redsponge.redengine.screen.components.SizeComponent;
import com.redsponge.redengine.utils.Logger;

public class RenderSystem extends SortedIteratingSystem {

    private FitViewport viewport;
    private SpriteBatch batch;
    private Color background;

    public RenderSystem(int width, int height, SpriteBatch batch) {
        super(Family.all(PositionComponent.class, SizeComponent.class, RenderComponent.class).get(), ZComparator.instance);
        this.batch = batch;
        viewport = new FitViewport(width, height);
        viewport.apply(true);
        background = Color.BLACK;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = Mappers.position.get(entity);
        SizeComponent size = Mappers.size.get(entity);
        RenderComponent render = Mappers.render.get(entity);

        TextureRegion drawn = render.getRegion();
        boolean flipX = render.isFlipX();
        boolean flipY = render.isFlipY();

        if (drawn == null || drawn.getTexture() == null) return;

        float width;
        float height;
        if(render.isUseRegW()) {
            width = drawn.getRegionWidth();
        } else {
            width = size.getX();
        }

        if(render.isUseRegH()) {
            height = drawn.getRegionHeight();
        } else {
            height = size.getY();
        }

        width *= render.getScaleX();
        height *= render.getScaleY();

        drawn.flip(flipX, flipY);
        batch.draw(drawn, pos.getX() + render.getOffsetX(), pos.getY() + render.getOffsetY(), width, height);
        drawn.flip(flipX, flipY);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
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

    public RenderSystem setBackground(Color background) {
        this.background.set(background);
        return this;
    }

    public Color getBackground() {
        return background;
    }
}
