package com.redsponge.redengine.screen.systems;

import com.badlogic.ashley.core.Entity;
import com.redsponge.redengine.screen.components.Mappers;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    public static final ZComparator instance = new ZComparator();

    @Override
    public int compare(Entity o1, Entity o2) {
        int a = Mappers.position.get(o1).getZ();
        int b = Mappers.position.get(o2).getZ();

        return a - b;
    }
}
