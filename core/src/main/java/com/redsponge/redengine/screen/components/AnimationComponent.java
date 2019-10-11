package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationComponent implements Component {

    public static final int NO_FIXED_FRAME = -1;

    private Animation<TextureRegion> animation;
    private float animationTime;
    private int fixedFrame = NO_FIXED_FRAME;
    private float animationSpeed;

    public AnimationComponent() {
        this.animationSpeed = 1;
    }

    public AnimationComponent(Animation<TextureRegion> animation) {
        this();
        this.animation = animation;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public AnimationComponent setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        return this;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public AnimationComponent setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
        return this;
    }

    public int getFixedFrame() {
        return fixedFrame;
    }

    public AnimationComponent setFixedFrame(int fixedFrame) {
        this.fixedFrame = fixedFrame;
        return this;
    }

    public float getAnimationSpeed() {
        return animationSpeed;
    }

    public AnimationComponent setAnimationSpeed(float animationSpeed) {
        this.animationSpeed = animationSpeed;
        return this;
    }
}
