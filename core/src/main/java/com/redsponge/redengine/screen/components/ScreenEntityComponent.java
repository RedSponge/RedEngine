package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;
import com.redsponge.redengine.screen.entity.ScreenEntity;

public class ScreenEntityComponent implements Component {

    private ScreenEntity screenEntity;

    public ScreenEntityComponent(ScreenEntity screenEntity) {
        this.screenEntity = screenEntity;
    }

    public ScreenEntity getScreenEntity() {
        return screenEntity;
    }
}
