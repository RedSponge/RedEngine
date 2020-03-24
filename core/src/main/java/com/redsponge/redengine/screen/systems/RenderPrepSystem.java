package com.redsponge.redengine.screen.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.redsponge.redengine.screen.components.AnimationComponent;
import com.redsponge.redengine.screen.components.Mappers;
import com.redsponge.redengine.screen.components.NinePatchComponent;
import com.redsponge.redengine.screen.components.RenderComponent;
import com.redsponge.redengine.screen.components.TextureComponent;

public class RenderPrepSystem extends IteratingSystem {

    public RenderPrepSystem() {
        super(Family.all(RenderComponent.class).one(TextureComponent.class, AnimationComponent.class, NinePatchComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent texC = Mappers.texture.get(entity);
        AnimationComponent aniC = Mappers.animation.get(entity);
        RenderComponent renderC = Mappers.render.get(entity);
        NinePatchComponent ninePatch = Mappers.ninePatch.get(entity);

        if(texC != null) {
            renderC.getRegion().setRegion(texC.getTexture());
        }
        if(aniC != null) {
            // Tick animation 
            aniC.setAnimationTime(aniC.getAnimationTime() + deltaTime * aniC.getAnimationSpeed());

            Animation<TextureRegion> ani = aniC.getAnimation();
            TextureRegion reg;
            if(aniC.getFixedFrame() == AnimationComponent.NO_FIXED_FRAME) {
                reg = ani.getKeyFrame(aniC.getAnimationTime());
            } else {
                reg = ani.getKeyFrames()[aniC.getFixedFrame()];
            }
            renderC.getRegion().setRegion(reg);
        }
        if(ninePatch != null) {
            renderC.getRegion().setRegion(ninePatch.getPatch().getTexture());
        }
    }
}
