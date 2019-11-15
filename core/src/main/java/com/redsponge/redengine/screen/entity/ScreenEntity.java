package com.redsponge.redengine.screen.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.screen.INotified;
import com.redsponge.redengine.screen.components.PositionComponent;
import com.redsponge.redengine.screen.components.RenderComponent;
import com.redsponge.redengine.screen.components.SizeComponent;
import com.redsponge.redengine.screen.components.VelocityComponent;
import com.redsponge.redengine.utils.Logger;

public abstract class ScreenEntity extends Entity {

    protected AbstractScreen screen;
    protected AssetSpecifier assets;

    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;

    private Object tag;

    protected PositionComponent pos;
    protected SizeComponent size;
    protected RenderComponent render;
    protected VelocityComponent vel;

    public ScreenEntity() {}

    public ScreenEntity(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
    }

    /**
     * Sends a notification to the screen which will be broadcasted to every {@link INotified} in the screen
     * @param notification The notification to send
     */
    protected final void notifyScreen(int notification) {
        screen.notified(this, notification);
    }

    public final void remove() {
        screen.removeEntity(this);
    }

    public final void addedToScreen() {
        Logger.log(this, "Default Added Method");
        pos = new PositionComponent();
        size = new SizeComponent();
        render = new RenderComponent();
        vel = new VelocityComponent();

        add(pos);
        add(size);
        add(render);
        add(vel);

        added();
    }
    /**
     * Called when the entity is added to the screen.
     */
    public void added() {

    }

    public void addedToEngine() {

    }

    public void additionalTick(float delta) {

    }

    public void additionalRender() {

    }

    public int getZ() {
        return 0;
    }

    public void removed() {

    }

    public void setScreen(AbstractScreen screen) {
        this.screen = screen;
    }

    public void setAssets(AssetSpecifier assets) {
        this.assets = assets;
        loadAssets();
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public AbstractScreen getScreen() {
        return screen;
    }

    public AssetSpecifier getAssets() {
        return assets;
    }

    /**
     * Called after {@link ScreenEntity#setAssets(AssetSpecifier)}
     * Inside it you should assign any assets to their fields
     */
    public void loadAssets() {}
}
