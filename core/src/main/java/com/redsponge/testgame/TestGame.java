package com.redsponge.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.redsponge.redengine.EngineGame;

public class TestGame extends EngineGame {

    private Texture testingImage;
    private ShaderProgram shader;

    @Override
    public void init() {
        testingImage = new Texture("testing_image.png");
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(Gdx.files.internal("shaders/vertex_passthrough.vert"),
                Gdx.files.internal("shaders/fragment_passthrough.frag"));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.setShader(shader);
        System.out.println(shader.getLog());
        batch.draw(testingImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        testingImage.dispose();
    }
}
