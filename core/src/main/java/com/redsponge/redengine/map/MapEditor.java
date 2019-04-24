package com.redsponge.redengine.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.redengine.map.events.EventChangeListener;
import com.redsponge.redengine.map.events.EventTile;
import com.redsponge.redengine.utils.IntVector2;
import com.redsponge.redengine.utils.Logger;

public class MapEditor extends InputAdapter implements Disposable {

    private short[][] mapGrid;
    private Array<EventTile> events;
    private EventTile selectedEvent;

    private Viewport viewport;
    private int cellSize;
    private int lastX, lastY;
    private TileBatch tileBatch;

    private TileGroup[] groups;

    private int selectedTile;
    private Texture eventTex;
    private NinePatch eventNP;

    private boolean eventMode;
    private boolean creatingEvent;

    private EventChangeListener eventChangeListener;

    private int newEventX, newEventY, newEventW, newEventH;

    public MapEditor(Viewport viewport, int width, int height, int cellSize) {
        this.viewport = viewport;
        this.mapGrid = new short[width][height];
        this.eventMode = false;

        eventTex = new Texture("event_tile.png");
        eventNP = new NinePatch(eventTex, 1, 1, 1, 1);

        events = new Array<EventTile>();
        events.add(new EventTile(2, 2, 5, 2, eventNP));

        eventMode = true;
        creatingEvent = false;

        this.cellSize = cellSize;

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
        batch.setColor(Color.WHITE);
        processKeys();
        batch.begin();
        renderLayer(mapGrid, batch);

        for(EventTile e : events) {
            e.renderOnMap(batch, cellSize);
        }
        if(selectedEvent != null) {
            selectedEvent.renderOnMap(batch, cellSize); // Highlight selected
        }

        if(creatingEvent) {
            eventNP.draw(batch, newEventX * cellSize, newEventY * cellSize, newEventW * cellSize, newEventH * cellSize);
        }

        renderTilePreview(batch);
        batch.end();
        renderGrid(renderer);
    }

    private void processKeys() {
        if(eventMode) {
            if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
                if(creatingEvent) {
                    creatingEvent = false;
                }
            }
        }
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

        newEventX = 0;
        newEventY = 0;
        newEventW = 0;
        newEventH = 0;

        if(eventMode) {
            selectEvent();
            processEventCreation(false);
        } else {
            markSpot(screenX, screenY);
        }
        return true;
    }

    private void processEventCreation(boolean dragged) {
        creatingEvent = true;
        IntVector2 projected = new IntVector2(viewport.unproject(new Vector2(lastX, lastY)));
        projected.scl(1f / cellSize);

        if(dragged) {
            newEventW = projected.x - newEventX;
            newEventH = projected.y - newEventY;
            if(newEventW >= 0) newEventW++;
            if(newEventH >= 0) newEventH++;
        } else {
            newEventX = projected.x;
            newEventY = projected.y;
        }
    }

    private void selectEvent() {
        IntVector2 projected = new IntVector2(viewport.unproject(new Vector2(lastX, lastY)));
        for (EventTile e : events) {
            if (e.mouseInside(projected.x, projected.y, cellSize)) {
                if(selectedEvent != null) {
                    eventChangeListener.deselectedEvent(selectedEvent);
                }
                selectedEvent = e;
                eventChangeListener.selectedNewEvent(selectedEvent, false);
                Logger.log(this, "Selected event!");
                return;
            }
        }
        if(selectedEvent != null) {
            eventChangeListener.deselectedEvent(selectedEvent);
        }
        selectedEvent = null;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        lastX = screenX;
        lastY = screenY;

        if(creatingEvent)
            createEvent();

        newEventX = 0;
        newEventY = 0;
        newEventW = 0;
        newEventH = 0;
        return true;
    }

    private void createEvent() {
        if(newEventW == 0 || newEventH == 0) {
            Logger.log(this, "Event size is 0!");
            return;
        }
        if(newEventX < 0 || newEventY < 0 || newEventX > mapGrid[0].length - 1 || newEventY > mapGrid.length - 1) {
            Logger.log(this, "Event out of map!");
            return;
        }
        EventTile newEvent = new EventTile(newEventX, newEventY, newEventW, newEventH, eventNP);
        events.add(newEvent);
        selectedEvent = newEvent;
        if(eventChangeListener != null) {
            eventChangeListener.selectedNewEvent(selectedEvent, true);
        }
        Logger.log(this, newEventX, newEventY, newEventW, newEventH);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        lastX = screenX;
        lastY = screenY;

        if(eventMode && creatingEvent) {
            processEventCreation(true);
        } else {
            markSpot(screenX, screenY);
        }
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

    @Override
    public boolean keyTyped(char character) {
        System.out.println(character);
        if(character == 'e' || character == 'E') {
            eventMode = !eventMode;
        }
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

    public EventTile getSelectedEvent() {
        return selectedEvent;
    }

    public boolean isEventSelected() {
        return selectedEvent != null;
    }
    // endregion

    @Override
    public void dispose() {
        tileBatch.dispose();
    }

    public void removeSelectedEvent() {
        events.removeValue(selectedEvent, true);
        if(eventChangeListener != null) {
            eventChangeListener.deletedEvent(selectedEvent);
        }
        selectedEvent = null;
    }

    public void setEventChangeListener(EventChangeListener eventChangeListener) {
        this.eventChangeListener = eventChangeListener;
    }
}
