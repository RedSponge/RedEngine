package com.redsponge.redengine.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.redengine.assets.Asset;
import com.redsponge.redengine.screen.AbstractScreen;
import com.redsponge.redengine.utils.GameAccessor;
import com.redsponge.redengine.utils.holders.Pair;

/**
 * An example to test the {@link LightSystem}
 */
public class LightTestScreen extends AbstractScreen implements InputProcessor {

    private FitViewport viewport;

    @Asset(path = "world_tiles.png")
    private Texture myTexture;
    private Vector2 pos;

    private int pixelWidth = 320*3, pixelHeight = 240*3;

    @Asset(path = "light/point_light.png")
    private Texture light;

    private float[][] vertices;

    public LightTestScreen(GameAccessor ga) {
        super(ga);
        viewport = new FitViewport(pixelWidth, pixelHeight);
    }

    @Override
    public void show() {
        pos = new Vector2();
        Gdx.input.setInputProcessor(this);
        vertices = new float[][] {
                {100, 100, 100, 200, 200, 200},
                {0, 0, 0, pixelHeight, pixelWidth, pixelHeight, pixelWidth, 0}
        };
    }

    @Override
    public void tick(float delta) {
        if(Gdx.input.isKeyPressed(Keys.SPACE)) {
            viewport.getCamera().position.x++;
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(myTexture, 0, 0, 1500, 1500);
        batch.end();

        float rad = 200;

        Array<Pair<Vector2, Vector2>> intersections = new Array<>();
        Circle lightCircle = new Circle(pos, rad);

        for (float[] vertex : vertices) {
            for(int i = 0; i < vertex.length / 2; i++) {
                Vector2 point = new Vector2(vertex[i * 2], vertex[1 + i * 2]);
                Vector2 local = new Vector2(point);
                if(lightCircle.contains(point)) {
                    float angle = pos.angle(local);

                    float dx = (float) Math.cos(angle * MathUtils.degRad);
                    float dy = (float) Math.sin(angle * MathUtils.degRad);
                    point.lerp(pos, -rad);
                    intersections.add(new Pair<>(local, point));
                }
            }
        }

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        for (float[] vertex : vertices) {
            for(int i = 0; i < vertex.length / 2; i++) {
                shapeRenderer.circle(vertex[i * 2], vertex[1 + i * 2], 10);
            }
        }
        for (Pair<Vector2, Vector2> intersection : intersections) {
//            shapeRenderer.line(intersection.a, pos);
            shapeRenderer.line(intersection.a, intersection.b);
        }
        shapeRenderer.circle(pos.x, pos.y, rad, 20);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector2 p = viewport.unproject(new Vector2(screenX, screenY));
        pos.set(p.x, viewport.getWorldHeight() - p.y);
        pos.set(p);
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
