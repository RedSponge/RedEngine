package com.redsponge.test.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.redsponge.redengine.physics.PBodyType;
import com.redsponge.redengine.physics.PSolid;
import com.redsponge.redengine.screen.components.AnimationComponent;
import com.redsponge.redengine.screen.components.PhysicsComponent;
import com.redsponge.redengine.screen.entity.RenderPreset;
import com.redsponge.redengine.screen.entity.ScreenEntity;
import com.redsponge.redengine.utils.Logger;

public class PlatformerPlayer extends ScreenEntity {

    private class PlayerAnimation {

        public PlayerAnimation(Animation<TextureRegion> anim, RenderPreset preset) {
            this.anim = anim;
            this.preset = preset;
        }

        public Animation<TextureRegion> getAnim() {
            return anim;
        }

        public RenderPreset getPreset() {
            return preset;
        }

        private final Animation<TextureRegion> anim;
        private final RenderPreset preset;
    }

    private Animation<TextureRegion> idleAnim;
    private RenderPreset idlePreset;

    private Animation<TextureRegion> walkAnim;
    private RenderPreset walkPreset;

    private Animation<TextureRegion> jumpAnim;
    private RenderPreset jumpPreset;

    private PlayerAnimation currentAnimation;

    private PlayerAnimation idleAnimation;
    private PlayerAnimation walkAnimation;
    private PlayerAnimation jumpAnimation;

    private AnimationComponent anim;
    private PhysicsComponent physics;
    private boolean onGround;

    private boolean jumping;
    private float jumpTime;

    @Override
    public void added() {
        pos.set(100, 100);
        size.set(8, 24);
        render.setUseRegW(true).setUseRegH(true);
        physics = new PhysicsComponent(PBodyType.ACTOR);
        add(physics);
        physics.setOnCollideY(this::collideY);
        render.setOffsetY(-2).setOffsetX(-4);
    }

    @Override
    public void loadAssets() {
        idleAnim = assets.getAnimation("idle");
        idlePreset = new RenderPreset(-4, -2, 1, 1);
        walkAnim = assets.getAnimation("walk");
        walkPreset = new RenderPreset(-4, 0, 1, 1);
        jumpAnim = assets.getAnimation("jump");
        jumpPreset = new RenderPreset(-4, 0, 1, 1);

        idleAnimation = new PlayerAnimation(idleAnim, idlePreset);
        walkAnimation = new PlayerAnimation(walkAnim, walkPreset);
        jumpAnimation = new PlayerAnimation(jumpAnim, jumpPreset);

        anim = new AnimationComponent();
        add(anim);

        setAnimation(idleAnimation);
    }

    private void setAnimation(PlayerAnimation anim) {
        if(anim != currentAnimation) {
            currentAnimation = anim;
            anim.preset.applyTo(render);
            this.anim.setAnimation(anim.anim);
        }
    }

    @Override
    public void additionalTick(float delta) {
        boolean right = Gdx.input.isKeyPressed(Keys.RIGHT);
        boolean left = Gdx.input.isKeyPressed(Keys.LEFT);
        boolean up = Gdx.input.isKeyPressed(Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Keys.DOWN);

        int vx = (right ? 1 : 0) - (left ? 1 : 0);

        if(vx != 0) {
            vel.setX(vx * 3);
        } else {
            vel.setX(vel.getX() * 0.9f);
            if(Math.abs(vel.getX()) < 0.5f)  {
                vel.setX(0);
            }
        }
        processJump(delta);
        Logger.log(this, jumping, jumpTime);
        if(!jumping) {
            vel.setY(vel.getY() - .5f);
        }
        if(vx != 0) {
            render.setFlipX(vx < 0);
        }

        if(Gdx.input.isKeyJustPressed(Keys.J)) {
            pos.setY(pos.getY() + 100);
            vel.setY(0);
        }

        if(!onGround) {
            setAnimation(jumpAnimation);
        } else if(vx == 0) {
            setAnimation(idleAnimation);
        } else {
            setAnimation(walkAnimation);
        }
    }

    private void processJump(float delta) {
        Logger.log(this, delta);
        float len = 0.3f;
        if(!jumping) {
            if(Gdx.input.isKeyPressed(Keys.SPACE) && onGround) {
                jumping = true;
                onGround = false;
            }
        }

        if(jumping) {
            vel.setY(Interpolation.linear.apply((len - jumpTime) / len) * 5);
            jumpTime += delta;
            if(jumpTime > len || !Gdx.input.isKeyPressed(Keys.SPACE)) {
                jumping = false;
                jumpTime = 0;
            }
        }
    }

    private void collideY(PSolid pSolid) {
        if(pSolid.getConnectedEntity() instanceof IPlatform) {
            if(vel.getY() < 0) {
                onGround = true;
            }
            vel.setY(0);
        }
    }
}
