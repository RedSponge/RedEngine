package com.redsponge.redengine.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.redsponge.redengine.utils.GeneralUtils;

public class TileSelectButton extends Button {

    private TileGroup tile;
    private int index;
    private MapEditor editor;

    private float neededScale;

    public TileSelectButton(TileGroup tile, MapEditor editor) {
        this.tile = tile;
        neededScale = 1;
        setSize(48, 48);

        this.editor = editor;
        this.index = tile.getIndex();

        this.addListener(new ClickListener() {
            boolean inside = false;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                neededScale = 0.9f;
                editor.setSelectedTile(tile.getIndex());
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(inside)
                    neededScale = 1.1f;
                else
                    neededScale = 1;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                inside = true;
                if(pointer == -1)
                    neededScale = 1.1f;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if(pointer == -1)
                    neededScale = 1;
                    inside = false;
            }
        });
    }

    @Override
    public void act(float delta) {
        setScale(GeneralUtils.lerp(getScaleX(), neededScale, 0.8f));

        setX(getParent().getWidth() / 2 - getWidth() * getScaleX() / 2);
        setY(getStage().getViewport().getWorldHeight() - (index + 0.5f) * (getHeight() + 16) - 16 - getHeight() * getScaleY() / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tile.getRepresentingRegion(), getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
    }
}
