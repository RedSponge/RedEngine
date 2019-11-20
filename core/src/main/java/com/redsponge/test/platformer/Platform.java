package com.redsponge.test.platformer;

import com.redsponge.redengine.physics.PBodyType;
import com.redsponge.redengine.screen.components.PhysicsComponent;
import com.redsponge.redengine.screen.entity.ScreenEntity;

public class Platform extends ScreenEntity implements IPlatform {

    private int x, y, w, h;
    public Platform(int x, int y, int w, int h) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }


    @Override
    public void added() {
        pos.set(x, y);
        size.set(w, h);
        PhysicsComponent phys = new PhysicsComponent(PBodyType.SOLID);
        add(phys);
    }
}
