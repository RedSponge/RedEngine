package com.redsponge.redengine.transitions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class TextureTransition extends Transition {

    private Texture texture;
    private ScalingViewport viewport;
    private ShaderProgram shader;
    private boolean shouldFlipInShader;

    public TextureTransition(Interpolation interFrom, Interpolation interTo, SpriteBatch batch, ShapeRenderer shapeRenderer, float length, Texture texture) {
        this(interFrom, interTo, batch, shapeRenderer, length, texture, true);
    }

    public TextureTransition(Interpolation interFrom, Interpolation interTo, SpriteBatch batch, ShapeRenderer shapeRenderer, float length, Texture texture, boolean shouldFlipInShader) {
        super(interFrom, interTo, batch, shapeRenderer, length);
        this.texture = texture;
        this.viewport = new ScalingViewport(Scaling.fill, texture.getWidth(), texture.getHeight());
        this.shouldFlipInShader = shouldFlipInShader;
        this.shader = TransitionTextures.getInstance().mixShader;
    }

    @Override
    public void render(float time) {
        float progress = getProgress(time, true);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.setShader(this.shader);
        batch.begin();

        shader.setUniformf("u_progress", progress);
        shader.setUniformf("blackColor", 0, 0, 0, 1);
        shader.setUniformf("whiteColor", 1, 1, 1, 0);
        shader.setUniformf("flipped", shouldFlipInShader ? (time > length / 2 ? 1 : 0) : 0);

        batch.draw(texture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.setShader(null);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {

    }
}
