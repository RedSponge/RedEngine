package com.redsponge.redengine.map;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.utils.IntVector2;

public class MapEditor extends InputAdapter implements Disposable {

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

    /**
     * Renders a single tile layer
     * @param grid The layer to render, values are tile types
     * @param batch The {@link SpriteBatch} to use
     */
    private void renderLayer(short[][] grid, SpriteBatch batch) {
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[x].length; y++) {
                if(grid[y][x] != 0) {
                    TextureRegion tile = groups[grid[y][x] - 1].getRegion(x, y, grid);
                    batch.draw(tile, x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    /**
     * Renders the editor
     * @param batch The {@link SpriteBatch} to use
     * @param renderer The {@link ShapeRenderer} to use
     */
    public void render(SpriteBatch batch, ShapeRenderer renderer) {
        batch.begin();
        renderLayer(mapGrid, batch);
        renderLayer(foregroundGrid, batch);

        renderTilePreview(batch);
        batch.end();
        renderGrid(renderer);
    }

    /**
     * Renders the transparent tile that shows up in your mouse's position and previews the next tile
     * @param batch The {@link SpriteBatch} to use
     */
    private void renderTilePreview(SpriteBatch batch) {
        IntVector2 projected = new IntVector2(viewport.unproject(new Vector2(lastX, lastY)));
        if(selectedTile != 0 && projected.x >= 0 && projected.y >= 0) {
            batch.setColor(new Color(1, 1, 1, 0.5f));
            batch.draw(tileBatch.getTile((selectedTile - 1) * 4), projected.x / cellSize * cellSize, projected.y / cellSize * cellSize, cellSize, cellSize);
            batch.setColor(Color.WHITE);
        }
    }

    /**
     * Renders the overlapping grid
     * @param renderer The {@link ShapeRenderer} to use
     */
    private void renderGrid(ShapeRenderer renderer) {
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

    /**
     * Marks a spot in the map based on screen coords
     * @param screenX The mouse's screen x
     * @param screenY The mouse's screen y
     */
    public void markSpot(int screenX, int screenY) {
        IntVector2 projected = new IntVector2(viewport.unproject(new Vector2(lastX, lastY)));
        int y = projected.y / cellSize;
        int x = projected.x / cellSize;
        if(projected.x < 0 || projected.y < 0 || y > mapGrid.length - 1 || x > mapGrid[0].length - 1) {
            return;
        }
        mapGrid[y][x] = (short) selectedTile;
    }


    // region inputs

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastX = screenX;
        lastY = screenY;

        markSpot(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        lastX = screenX;
        lastY = screenY;

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        lastX = screenX;
        lastY = screenY;

        markSpot(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        lastX = screenX;
        lastY = screenY;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        ((OrthographicCamera) viewport.getCamera()).zoom += amount * 0.05f;
        return true;
    }
    // endregion


    // region getters and setters
    public void setSelectedTile(int selectedTile) {
        this.selectedTile = selectedTile;
    }
    public TileGroup[] getGroups() {
        return groups;
    }
    // endregion

    @Override
    public void dispose() {
        tileBatch.dispose();
    }


}
