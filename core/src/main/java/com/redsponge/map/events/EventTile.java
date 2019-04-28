package com.redsponge.map.events;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class EventTile {

    private int x, y;
    private int width, height;

    private boolean happenOnce;

    private String eventId;
    private HashMap<String, Object> params;
    private NinePatch texR;
    private boolean selected;

    public EventTile(int x, int y, int width, int height, NinePatch texR) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texR = texR;
        this.params = new HashMap<String, Object>();

        if(width < 0) {
            this.x += width;
            this.width *= -1;
        }
        if(height < 0) {
            this.y += height;
            this.height *= -1;
        }
    }

    public void renderOnMap(SpriteBatch batch, int tileSize) {
        texR.draw(batch, x * tileSize, y * tileSize, width * tileSize, height * tileSize);
    }

    public boolean mouseInside(int mx, int my, int tileSize) {
        return mx > this.x * tileSize && mx < this.x * tileSize + this.width * tileSize && my > this.y * tileSize && my < this.y * tileSize + this.height * tileSize;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public HashMap<String, Object> getParameters() {
        return params;
    }

    public void setHappenOnce(boolean happenOnce) {
        this.happenOnce = happenOnce;
    }

    public boolean doesHappenOnce() {
        return happenOnce;
    }

    @Override
    public String toString() {
        return String.format("Event %s", eventId);
    }
}
