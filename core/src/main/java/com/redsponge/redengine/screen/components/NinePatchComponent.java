package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class NinePatchComponent implements Component {

    private NinePatch patch;

    public NinePatchComponent(NinePatch patch) {
        this.patch = patch;
    }

    public NinePatch getPatch() {
        return patch;
    }

    public NinePatchComponent setPatch(NinePatch patch) {
        this.patch = patch;
        return this;
    }
}
