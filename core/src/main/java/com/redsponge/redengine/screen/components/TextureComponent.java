package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {

    private TextureRegion texture;

    public TextureComponent(Texture texture) {
        this.texture = new TextureRegion(texture);
    }

    public TextureComponent(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public TextureComponent setTexture(Texture texture) {
        this.texture.setRegion(texture);
        return this;
    }

    public TextureComponent setTexture(TextureRegion texture) {
        this.texture.setRegion(texture);
        return this;
    }
}
