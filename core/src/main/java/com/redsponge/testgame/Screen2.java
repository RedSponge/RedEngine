package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.screen.MapEditScreen;
import com.redsponge.redengine.transitions.TransitionTemplates;
import com.redsponge.redengine.utils.GameAccessor;

public class Screen2 extends AbstractScreen {

    public Screen2(GameAccessor ga) {
        super(ga);
    }

    @Override
    public void tick(float delta) {
        if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            ga.transitionTo(new Screen1(ga), TransitionTemplates.sineSlide(1));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
