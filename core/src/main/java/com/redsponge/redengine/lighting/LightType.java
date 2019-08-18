package com.redsponge.redengine.lighting;

import com.badlogic.gdx.graphics.GL20;

public enum LightType {
    ADDITIVE(GL20.GL_ONE, GL20.GL_ONE),
    MULTIPLICATIVE(GL20.GL_ZERO, GL20.GL_SRC_COLOR)


    ;
    private final int srcBlend;
    private final int dstBlend;

    LightType(int srcBlend, int dstBlend) {
        this.srcBlend = srcBlend;
        this.dstBlend = dstBlend;
    }

    public int getSrcBlend() {
        return srcBlend;
    }

    public int getDstBlend() {
        return dstBlend;
    }
}
