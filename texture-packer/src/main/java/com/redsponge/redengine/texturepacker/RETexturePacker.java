package com.redsponge.redengine.texturepacker;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class RETexturePacker {

    public static void main(String[] args) {
        TexturePacker.processIfModified("assets/lights", "../assets/lights", "lights");
        TexturePacker.processIfModified("assets/entities", "../assets/entities", "entities");
    }

}
