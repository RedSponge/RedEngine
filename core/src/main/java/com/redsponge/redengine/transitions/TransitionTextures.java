package com.redsponge.redengine.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.redsponge.redengine.render.util.NoiseGenerator;
import com.redsponge.redengine.utils.GeneralUtils;
import com.redsponge.redengine.utils.Logger;

public class TransitionTextures {

    public final ShaderProgram mixShader;
    public final Texture slide;
    public final Texture dissolve;
    public final Texture circleOut;
    public final Texture circleIn;
    public final Texture test;

    private TransitionTextures() {
        mixShader = GeneralUtils.tryLoadShader(Gdx.files.internal("shaders/transition.vsh"), Gdx.files.internal("shaders/transition.fsh"));
        slide = new Texture("transition/slide.png");
        dissolve = new Texture("transition/dissolve.png");
        circleOut = new Texture("transition/circleOut.png");
        circleIn = new Texture("transition/circleIn.png");

        test = new Texture("transition/test.png");
    }

    private static TransitionTextures instance = new TransitionTextures();

    public static TransitionTextures getInstance() {
        return instance;
    }

    /**
     * Done so that there won't be a wait on the 1st time a transition is used because of just-in-time compiling
     */
    public void load() {
        Logger.log(this, "Loaded Transition Textures");
    }
}
