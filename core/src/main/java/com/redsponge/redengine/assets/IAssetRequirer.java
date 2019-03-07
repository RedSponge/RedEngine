package com.redsponge.redengine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.redsponge.redengine.screen.AbstractScreen;

/**
 * Marks a class as a thing that can be loaded by {@link Assets#load(AbstractScreen)}
 */
public interface IAssetRequirer {

    /**
     * Gets all assets that should be loaded for this screen
     * @return an array containing {@link AssetDescriptor}s which describe what should be loaded/unloaded
     */
    AssetDescriptor[] getRequiredAssets();

}
