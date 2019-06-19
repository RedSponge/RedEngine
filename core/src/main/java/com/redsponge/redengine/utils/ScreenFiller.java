package com.redsponge.redengine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/**
 * Fills the screen with a certain color and allows automatic blending.
 */
public class ScreenFiller {

    private static ScalingViewport fillViewport;

    public static void init() {
        fillViewport = new ScalingViewport(Scaling.fill, 1, 1);
    }

    /**
     * Fills a screen with blending enabled
     * @param shapeRenderer The {@link ShapeRenderer} to use to render
     * @param color The color to fill the screen with
     */
    public static void fillScreen(ShapeRenderer shapeRenderer, Color color) {
        fillScreen(shapeRenderer, color.r, color.g, color.b, color.a);
    }

    /**
     * Fills a screen with blending enabled
     * @param shapeRenderer The {@link ShapeRenderer} to use to render.
     * @param r The red value
     * @param g The green value
     * @param b The blue value
     * @param a The alpha value. IT DOES HAVE AN ACTION
     */
    public static void fillScreen(ShapeRenderer shapeRenderer, float r, float g, float b, float a) {
        Matrix4 oldCombined = shapeRenderer.getProjectionMatrix();
        Color oldColor = shapeRenderer.getColor();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        fillViewport.apply(true);

        shapeRenderer.setProjectionMatrix(fillViewport.getCamera().combined);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(r, g, b, a);
        shapeRenderer.rect(0, 0, 1, 1);
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);


        shapeRenderer.setProjectionMatrix(oldCombined);
        shapeRenderer.setColor(oldColor);
    }

    public static void resize(int width, int height) {
        fillViewport.update(width, height, true);
    }

}
