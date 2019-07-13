package com.redsponge.redengine.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.utils.GameAccessor;

import java.util.Comparator;

public abstract class AbstractScreen extends ScreenAdapter implements INotified {

    protected ShapeRenderer shapeRenderer;
    protected SpriteBatch batch;
    protected AssetSpecifier assets;

    protected GameAccessor ga;
    protected boolean transitioning;

    private DelayedRemovalArray<ScreenEntity> entities;
    private DelayedRemovalArray<INotified> notifiedEntities;

    private Comparator<ScreenEntity> zComparator = Comparator.comparingInt(ScreenEntity::getZ);

    public AbstractScreen(GameAccessor ga) {
        this.ga = ga;
        this.shapeRenderer = ga.getShapeRenderer();
        this.batch = ga.getSpriteBatch();

        this.entities = new DelayedRemovalArray<>();
        this.notifiedEntities = new DelayedRemovalArray<>();
    }

    public void addEntity(ScreenEntity entity) {
        entities.add(entity);
        if(entity instanceof INotified) {
            notifiedEntities.add((INotified) entity);
        }

        entity.setScreen(this);
        entity.setAssets(assets);
        this.entities.sort(zComparator);
    }

    public void removeEntity(ScreenEntity entity) {
        entities.removeValue(entity, true);
        if(entity instanceof INotified) {
            notifiedEntities.removeValue((INotified) entity, true);
        }
        entity.removed();
        this.entities.sort(zComparator);
    }

    @Override
    public void show() {
        transitioning = false;
    }

    @Override
    public void notified(int notification) {
        for (INotified notifiedEntity : notifiedEntities) {
            notifiedEntity.notified(notification);
        }
    }

    @Override
    public final void render(float delta) {
        throw new RuntimeException("The default render method shouldn't be called when using an AbstractScreen!");
    }


    /**
     * Called before render, all logic should go here
     * @param delta The delta time from the last frame
     */
    public abstract void tick(float delta);

    /**
     * Called after tick, all rendering should go here
     */
    public abstract void render();

    public void tickEntities(float delta) {
        for (ScreenEntity entity : entities) {
            entity.tick(delta);
        }
    }

    public void renderEntities() {
        for (ScreenEntity entity : entities) {
            entity.render();
        }
    }

    /**
     * Should the screen be disposed when hidden?
     * @return Should the screen be disposed when hidden? defaults to false
     */
    public boolean shouldDispose() {
        return true;
    }

    public void beginTransition() {
        transitioning = true;
    }

    public AssetSpecifier getAssets() {
        return assets;
    }

    public void setAssets(AssetSpecifier assets) {
        this.assets = assets;
    }

    public void unloadAssetHolder() {
        if(assets != null) {
            assets.dispose();
        }
    }

    public abstract Class<? extends AssetSpecifier> getAssetSpecsType();
}
