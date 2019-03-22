package com.redsponge.redengine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.Logger;

/**
 * Handles all assets - loads and disposes
 */
public class Assets implements Disposable {

    public AssetManager am;

    private boolean updateStatus;

    public Assets() {
        am = new AssetManager();
    }

    /**
     * Loads in files into the {@link AssetManager} based on the passed screen's {@link AbstractScreen#getRequiredAssets()}
     * @param screen The screen to load from
     */
    public void load(AbstractScreen screen) {
        Logger.log(this, "[Loading assets for screen] ", screen);
        for(AssetDescriptor a : screen.getRequiredAssets()) {
            Logger.log(this, "[Loading]: ", a.fileName);
            am.load(a);
        }
        Logger.log(this, "[Finished loading assets for screen] ", screen);
    }

    /**
     * Updates the asset manager and returns the output
     * @return Whether or not loading is complete
     */
    public boolean updateAssetManager() {
        return am.update();
    }


    /**
     * Calculates the percent of assets loaded out of all
     * @return the percent of assets loaded out of all
     */
    @SuppressWarnings("unused")
    public float getPercentDone() {
        return (float) am.getLoadedAssets() / (am.getQueuedAssets() + am.getLoadedAssets());
    }



    /**
     * Unloads files from the {@link AssetManager} based on the passed requirer's {@link IAssetRequirer#getRequiredAssets()}
     * @param requirer The screen to unload from
     */
    public void unload(IAssetRequirer requirer) {
        Logger.log(this, "Unloading assets for screen ", requirer);
        for(AssetDescriptor a : requirer.getRequiredAssets()) {
            Logger.log(this, "Unloading: ", a.fileName);
            am.unload(a.fileName);
        }
        Logger.log(this, "Finished unloading assets for screen ", requirer);
    }



    /**
     * Retrieves an asset
     * @param descriptor The asset descriptor
     * @param <T> - The asset's type
     * @return The asset
     */
    public <T> T get(AssetDescriptor<T> descriptor) {
        return am.get(descriptor);
    }


    /**
     * Retrieves an asset
     * @param filename The asset's filename
     * @param type The asset's class
     * @param <T> - The asset's type
     * @return The asset
     */
    public <T> T get(String filename, Class<T> type) {
        return am.get(filename, type);
    }

    /**
     * Blocks until all assets are loaded.
     */
    public void finishLoading() {
        am.finishLoading();
    }

    @Override
    public void dispose() {
        am.dispose();
    }

}
