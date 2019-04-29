package com.redsponge.redengine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.redengine.utils.Logger;
import com.redsponge.redengine.utils.holders.Pair;
import com.redsponge.testgame.MyAssetHolder;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Handles all assets - loads and disposes
 */
public class Assets implements Disposable {

    public final AssetManager am;
    private boolean updateStatus;

    private HashMap<Field, Pair<AssetDescriptor, Object>> waitingValues;

    public Assets() {
        am = new AssetManager();
        waitingValues = new HashMap<>();
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
     * Unloads files from the {@link AssetManager} based on what's in {@link Assets#waitingValues}
     */
    public void unload() {
        for (Pair<AssetDescriptor, Object> value : waitingValues.values()) {
            Logger.log(this, "Unloading", value.a.fileName);
            am.unload(value.a.fileName);
        }
        waitingValues.clear();
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

    /**
     * Searches for fields annotated with {@link Asset} and loads them
     * @param requierer The requierer to search in
     */
    public void prepareAssets(IAssetRequirer requierer) {
        waitingValues.clear();

        Logger.log(this, "Began Loading", requierer);

        Class<?> cls = requierer.getClass();
        for (Field field : cls.getDeclaredFields()) {

            Asset assetAnnotation = field.getAnnotation(Asset.class);
            if(assetAnnotation != null) {
                AssetDescriptor descriptor = new AssetDescriptor<>(assetAnnotation.path(), field.getType());
                Logger.log(this, "Loading", descriptor.fileName);
                am.load(descriptor);
                waitingValues.put(field, new Pair<>(descriptor, requierer));
            }
        }

        Logger.log(this, "Finished Loading", requierer);
    }

    /**
     * Injects all fields loaded using {@link Assets#prepareAssets(IAssetRequirer)}
     * @param requierer The requierer to inject to
     */
    @SuppressWarnings("unchecked")
    public void injectAssets(IAssetRequirer requierer) {
        for (Field field : waitingValues.keySet()) {
            Pair<AssetDescriptor, Object> pair = waitingValues.get(field);
            Object value = get(pair.a);
            try {
                Logger.log(this, "Injecting", value);
                field.setAccessible(true);
                field.set(pair.b, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareAssetsRecursively(IAssetRequirer root) {
        Class<?> cls = root.getClass();
        for (Field field : cls.getDeclaredFields()) {

            Asset assetAnnotation = field.getAnnotation(Asset.class);
            if(assetAnnotation != null) {
                AssetDescriptor descriptor = new AssetDescriptor<>(assetAnnotation.path(), field.getType());
                Logger.log(this, "Loading", descriptor.fileName);
                am.load(descriptor);
                waitingValues.put(field, new Pair<>(descriptor, root));
            }

            if(IAssetRequirer.class.isAssignableFrom(field.getType())) {
                try {
                    field.setAccessible(true);
                    prepareAssetsRecursively((IAssetRequirer) field.get(root));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void dispose() {
        am.dispose();
    }



}
