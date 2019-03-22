package com.redsponge.redengine.map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.utils.IntVector2;

public class MapEditor implements InputProcessor {

    private short[][] mapGrid;
    private short[][] foregroundGrid;

    private Viewport viewport;
    private int cellSize;
    private int mapWidth, mapHeight;
    private int lastX, lastY;
    private TileBatch tileBatch;

    private TileGroup[] groups;

    private int selectedTile;

    public MapEditor(Viewport viewport, int width, int height, int cellSize) {
        this.viewport = viewport;
        this.mapGrid = new short[width][height];
        this.foregroundGrid = new short[width][height];

        this.cellSize = cellSize;

        this.mapWidth = width * cellSize;
        this.mapHeight = height * cellSize;

        tileBatch = new TileBatch(new Texture("world_tiles.png"), cellSize / 2);

        groups = new TileGroup[] {
                new TileGroup(0, 0, tileBatch, 1),
                new TileGroup(4, 0, tileBatch, 2),
        };
        selectedTile = 0;
    }

    public TileGroup[] getGroups() {
        return groups;
    }

    private void renderLayer(short[][] grid, SpriteBatch batch) {
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[x].length; y++) {
                if(grid[y][x] != 0) {
                    TextureRegion tile = tileBatch.getTile(grid[y][x] - 1);
                    if(grid[y][x] != 0) {
                        tile = groups[grid[y][x] - 1].getRegion(x, y, grid);
                    }
                    batch.draw(tile, x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    public void render(SpriteBatch batch, ShapeRenderer renderer) {
        batch.begin();
        renderLayer(mapGrid, batch);
        renderLayer(foregroundGrid, batch);

        IntVector2 projected = new IntVector2(viewport.unproject(new Vector2(lastX, lastY)));
        if(selectedTile != 0 && projected.x >= 0 && projected.y >= 0) {
            batch.setColor(new Color(1, 1, 1, 0.5f));
            batch.draw(tileBatch.getTile((selectedTile - 1) * 4), projected.x / cellSize * cellSize, projected.y / cellSize * cellSize, cellSize, cellSize);
            batch.setColor(Color.WHITE);
        }
        batch.end();

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Line);
        renderer.setColor(Color.LIGHT_GRAY);
        for(int x = 0; x <= mapGrid.length; x++) {
            for(int y = 0; y <= mapGrid[0].length; y++) {

                renderer.line(x * cellSize, 0, x * cellSize, y * cellSize);
                renderer.line(0, y * cellSize, x * cellSize, y * cellSize);

            }
        }
        renderer.end();
    }


    public boolean keyUp(int keycode) {
        return true;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastX = screenX;
        lastY = screenY;

        markSpot(screenX, screenY);
        return true;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        lastX = screenX;
        lastY = screenY;

        return true;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        lastX = screenX;
        lastY = screenY;

        markSpot(screenX, screenY);
        return true;
    }

    public void markSpot(int screenX, int screenY) {
        IntVector2 projected = new IntVector2(viewport.unproject(new Vector2(lastX, lastY)));
        int y = projected.y / cellSize;
        int x = projected.x / cellSize;
        if(projected.x < 0 || projected.y < 0 || y > mapGrid.length - 1 || x > mapGrid[0].length - 1) {
            return;
        }
        mapGrid[y][x] = (short) selectedTile;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        lastX = screenX;
        lastY = screenY;
        return false;
    }

    public boolean scrolled(int amount) {
        ((OrthographicCamera) viewport.getCamera()).zoom += amount * 0.05f;
        return true;
    }

    public void setSelectedTile(int selectedTile) {
        this.selectedTile = selectedTile;
    }

    public void dispose() {
        tileBatch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
