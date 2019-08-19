package com.redsponge.redengine.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.redengine.assets.AssetSpecifier;
import com.redsponge.redengine.utils.Logger;

public abstract class ScreenEntity {

    protected AbstractScreen screen;
    protected AssetSpecifier assets;

    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;

    public ScreenEntity(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
    }

    /**
     * Sends a notification to the screen which will be broadcasted to every {@link INotified} in the screen
     * @param notification The notification to send
     */
    protected final void notifyScreen(int notification) {
        screen.notified(notification);
    }

    public final void remove() {
        screen.removeEntity(this);
    }

    /**
     * Called when the entity is added to the screen.
     */
    public void added() {
        Logger.log(this, "Default Added Method");
    }

    public abstract void tick(float delta);

    public abstract void render();

    public int getZ() {
        return 0;
    }

    public abstract void removed();

    public void setScreen(AbstractScreen screen) {
        this.screen = screen;
    }

    public void setAssets(AssetSpecifier assets) {
        this.assets = assets;
        loadAssets();
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
