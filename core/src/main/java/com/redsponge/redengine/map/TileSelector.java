package com.redsponge.redengine.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TileSelector extends Group {

    private NinePatch selector;
    private boolean display;

    public TileSelector() {
        display = true;

        selector = new NinePatch(new Texture("selector_background.png"), 3, 3, 3, 3);
        setPosition(0, 0);

        addListener(new ClickListener() { // Clicking the selector should cancel the event
            @Override
            public void clicked(InputEvent event, float x, float y) {}
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setSize(getStage().getViewport().getWorldWidth() / 8, getStage().getViewport().getWorldHeight());
        selector.draw(batch, getX(), getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
    }

    @Override
    public boolean remove() {
        selector.getTexture().dispose();
        return super.remove();
    }

    public void toggle() {
        display = !display;
        addAction(Actions.moveTo(display ? 0 : -150, 0, 0.5f, display ? Interpolation.swingOut : Interpolation.swingIn));
    }
}
