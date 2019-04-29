package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.screen.MapEditScreen;
import com.redsponge.redengine.transitions.TransitionTemplates;
import com.redsponge.redengine.utils.GameAccessor;

public class Screen1 extends AbstractScreen {

    @Asset(path = "eraser.png")
    private Texture myTexture;

    private MyAssetHolder myAssetHolder;

    public Screen1(GameAccessor ga) {
        super(ga);

        myAssetHolder = new MyAssetHolder();
    }

    @Override
    public void tick(float delta) {
        if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            ga.transitionTo(new MapEditScreen(ga), TransitionTemplates.sineSlide(1));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.draw(myTexture, 100, 100);
        batch.draw(myAssetHolder.myTexture, 200, 200);
        batch.draw(myAssetHolder.worldTiles, 300, 300);
        batch.end();
    }
}
