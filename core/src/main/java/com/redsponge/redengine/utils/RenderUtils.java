package com.redsponge.redengine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class RenderUtils {

    private static ScalingViewport fillViewport;

    public static void init() {
        fillViewport = new ScalingViewport(Scaling.fill, 1, 1);
    }

    public static void fillScreen(ShapeRenderer shapeRenderer, Color color) {
        fillScreen(shapeRenderer, color.r, color.g, color.b, color.a);
    }

    public static void fillScreen(ShapeRenderer shapeRenderer, float r, float g, float b, float a) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        fillViewport.apply(true);
        shapeRenderer.setProjectionMatrix(fillViewport.getCamera().combined);

//        Color old = shapeRenderer.getColor();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(r, g, b, a);
        shapeRenderer.rect(0, 0, 1, 1);
        shapeRenderer.end();

//        shapeRenderer.setColor(old);
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public static void resize(int width, int height) {
        fillViewport.update(width, height, true);
    }

}
