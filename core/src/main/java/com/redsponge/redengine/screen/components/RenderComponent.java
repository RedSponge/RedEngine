package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponent implements Component {

    private boolean flipX, flipY;
    private TextureRegion region;

    public RenderComponent() {
        flipX = false;
        flipY = false;
        region = new TextureRegion();
    }

    public boolean isFlipX() {
        return flipX;
    }

    public RenderComponent setFlipX(boolean flipX) {
        this.flipX = flipX;
        return this;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public RenderComponent setFlipY(boolean flipY) {
        this.flipY = flipY;
        return this;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public RenderComponent setRegion(TextureRegion region) {
        this.region = region;
        return this;
    }
}
