package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class TextureComponent implements Component {

    private Texture texture;

    public TextureComponent(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureComponent setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }
}
