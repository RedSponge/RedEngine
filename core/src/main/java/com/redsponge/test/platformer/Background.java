package com.redsponge.test.platformer;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.redsponge.redengine.screen.components.TextureComponent;
import com.redsponge.redengine.screen.entity.ScreenEntity;

public class Background extends ScreenEntity {

    private TextureComponent tex;
    public Background() {
    }

    @Override
    public void added() {
        pos.set(0, 0);
        size.set(640, 360);

    }

    @Override
    public void loadAssets() {
        tex = new TextureComponent(assets.get("pixel", Texture.class));
        add(tex);
    }
}
