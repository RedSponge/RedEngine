package com.redsponge.redengine.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.screen.entity.ScreenEntity;
import com.redsponge.redengine.screen.entity.ScreenSystem;
import com.redsponge.redengine.utils.GameAccessor;
import com.redsponge.redengine.utils.GeneralUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

public abstract class AbstractScreen extends ScreenAdapter implements INotified {

    protected ShapeRenderer shapeRenderer;
    protected SpriteBatch batch;
    protected AssetSpecifier assets;

    protected GameAccessor ga;
    protected boolean transitioning;

    private DelayedRemovalArray<ScreenEntity> entities;
    private DelayedRemovalArray<INotified> notifiedEntities;

    private HashMap<Class<? extends ScreenSystem>, ScreenSystem> screenSystems;

    private Comparator<ScreenEntity> zComparator = Comparator.comparingInt(ScreenEntity::getZ);

    public AbstractScreen(GameAccessor ga) {
        this.ga = ga;
        this.shapeRenderer = ga.getShapeRenderer();
        this.batch = ga.getSpriteBatch();

        this.entities = new DelayedRemovalArray<>();
        this.notifiedEntities = new DelayedRemovalArray<>();
        this.screenSystems = new HashMap<>();
    }

    public void addEntity(ScreenEntity entity) {
        entities.add(entity);
        if(entity instanceof INotified) {
            notifiedEntities.add((INotified) entity);
        }

        entity.setScreen(this);
        entity.setAssets(assets);
        this.entities.sort(zComparator);
        entity.added();
    }

    public void removeEntity(ScreenEntity entity) {
        entities.removeValue(entity, true);
        if(entity instanceof INotified) {
            notifiedEntities.removeValue((INotified) entity, true);
        }
        entity.removed();
        this.entities.sort(zComparator);
    }

    /**
     * Adds a new system to the screen
     * @param system The new system's class
     * @param args The args for the system's constructor
     * @param <T> The System's type
     * @return The newly added system
     */
    public <T extends ScreenSystem> T addSystem(Class<T> system, Object... args) {
        try {
            T sys = system.getConstructor(Stream.of(args).map(Object::getClass).map(GeneralUtils::replaceWrappersWithPrimitives).toArray(Class[]::new)).newInstance(args);
            screenSystems.put(system, sys);
            return sys;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends ScreenSystem> T getSystem(Class<T> system) {
        return (T) screenSystems.get(system);
    }

    public void removeSystem(Class<? extends ScreenSystem> system) {
        screenSystems.remove(system).dispose();
    }

    @Override
    public void show() {
        transitioning = false;
    }

    @Override
    public void notified(Object notifier, int notification) {
        for (int i = 0; i < notifiedEntities.size; i++) {
            notifiedEntities.get(i).notified(notifier, notification);
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
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).tick(delta);
        }
    }

    public void renderEntities() {
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).render();
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

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void unloadAssetHolder() {
        if(assets != null) {
            assets.dispose();
        }
    }

    @Override
    public final void dispose() {
        for (ScreenSystem value : screenSystems.values()) {
            value.dispose();
        }
        screenSystems.clear();
        disposeAssets();
    }

    public void disposeAssets() {

    }

    public abstract Class<? extends AssetSpecifier> getAssetSpecsType();
}
