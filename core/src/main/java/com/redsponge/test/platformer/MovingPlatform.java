package com.redsponge.test.platformer;

public class MovingPlatform extends Platform {

    private float timePass;

    public MovingPlatform(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void added() {
        super.added();
        timePass = 0;
    }

    @Override
    public void additionalTick(float delta) {
        timePass += delta;
        vel.set(0, (float) (Math.sin(timePass * 3) * 1));
    }
}
