package com.redsponge.redengine.map;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.locks.StampedLock;

public class TileSelector extends Actor {

    private Pixmap pixmap;

    public TileSelector() {
        pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.drawPixel(0, 0, 0xFFFFFFFF);

        new HashSet<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
        }};

        new JFrame() {{
           setTitle("Howdy");
           setSize(50, 50);
           setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
           setVisible(true);
        }};

        StampedLock lock;
        Arrays.a
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public boolean remove() {
        pixmap.dispose();
        return super.remove();
    }
}
