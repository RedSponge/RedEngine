package com.redsponge.redengine.screen.entity;

public class ScreenEntityAttachment {

    private ScreenEntity entity;

    public ScreenEntityAttachment(ScreenEntity entity) {
        this.entity = entity;
    }

    public ScreenEntity getEntity() {
        return entity;
    }

    public void setEntity(ScreenEntity entity) {
        this.entity = entity;
    }
}
