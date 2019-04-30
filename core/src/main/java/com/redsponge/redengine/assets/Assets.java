package com.redsponge.redengine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.redengine.utils.GeneralUtils;
import com.redsponge.redengine.utils.Logger;
import com.redsponge.redengine.utils.holders.Pair;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Handles all assets - loads and disposes
 *
 * @see Asset
 */
public class Assets implements Disposable {

    public final AssetManager am;

    private HashMap<AssetDescriptor, Array<Pair<Field, Object>>> waitingValues;

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
        for (AssetDescriptor descriptor : waitingValues.keySet()) {
            Field keepingField = null;

            for (Pair<Field, Object> requirer : waitingValues.get(descriptor)) {

                Asset annotation = requirer.a.getAnnotation(Asset.class);
                if(annotation.shouldDispose()) {
                    keepingField = requirer.a;
                    break;
                }

            }

            if(keepingField == null) {
                Logger.log(this, "Unloading", descriptor.fileName);
                am.unload(descriptor.fileName);
            } else {
                Logger.log(this, "Skipped Unloading", descriptor.fileName, "Since field", keepingField, "Wants it loaded!");
            }
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
    @SuppressWarnings("unused")
    public <T> T get(String filename, Class<T> type) {
        return am.get(filename, type);
    }

    /**
     * Updates the asset manager until all assets are loaded.
     */
    @SuppressWarnings("unused")
    public void finishLoading() {
        am.finishLoading();
    }

    /**
     * Injects all fields loaded using {@link Assets#prepareAssets(IAssetRequirer)}
     */
    @SuppressWarnings("unchecked")
    public void injectAssets() {
        for (AssetDescriptor descriptor : waitingValues.keySet()) {
            Logger.log(this, "Injecting asset:", descriptor);

            Object asset = get(descriptor);
            for(Pair<Field, Object> pair : waitingValues.get(descriptor)) {
                Field field = pair.a;
                Object filled = pair.b;
                String display = GeneralUtils.getFieldName(field);
                try {
                    if(!field.isAnnotationPresent(DoNotLoad.class)) {
                        Logger.log(this, "Injected [", descriptor.fileName, "] Into", display);
                        field.setAccessible(true);
                        field.set(filled, asset);
                    }
                    else {
                        Logger.log(this, "[Skipped injecting] into", display, "since it's annotated with @DoNotLoad");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Finds all fields annotated with {@link Asset} and loads them. Fields annotated with {@link DoNotLoad} will NOT be loaded.
     * Also calls itself recursively for any {@link IAssetRequirer} field that is not annotated with {@link DoNotLoad}
     *
     *
     * @param root The root asset requirer, all of its fields will be checked.
     */
    public void prepareAssets(IAssetRequirer root) {
        Logger.log(this, "Began Loading For", root);
        Class<?> cls = root.getClass();
        for (Field field : cls.getDeclaredFields()) {

            Asset assetAnnotation = field.getAnnotation(Asset.class);
            if(!field.isAnnotationPresent(DoNotLoad.class) && assetAnnotation != null) {

                AssetDescriptor descriptor = new AssetDescriptor<>(assetAnnotation.path(), field.getType());
                Array<Pair<Field, Object>> toFill;

                // Load if not already loaded
                if(!waitingValues.containsKey(descriptor)) {

                    toFill = new Array<>();
                    waitingValues.put(descriptor, toFill);
                    Logger.log(this, "Loading", descriptor.fileName);
                    am.load(descriptor);

                }
                else {

                    toFill = waitingValues.get(descriptor);
                    Logger.log(this, "Skipping loading", descriptor.fileName, "Since already loaded");

                }

                // Adds to waiting list
                toFill.add(new Pair<>(field, root));
            }

            // Continue crawling through any IAssetRequirer fields which don't have @DoNotLoad applied
            if(IAssetRequirer.class.isAssignableFrom(field.getType())) {
                try {

                    field.setAccessible(true);
                    if(!field.isAnnotationPresent(DoNotLoad.class)) {
                        if(field.get(root) == null) {
                            field.set(root, field.getType().newInstance());
                        }
                        prepareAssets((IAssetRequirer) field.get(root));
                    }

                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        Logger.log(this, "Finished Loading For", root);
    }

    @Override
    public void dispose() {
        am.dispose();
    }



}
