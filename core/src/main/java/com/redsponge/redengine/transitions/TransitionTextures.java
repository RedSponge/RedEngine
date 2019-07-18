package com.redsponge.redengine.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.redengine.render.util.NoiseGenerator;
import com.redsponge.redengine.utils.GeneralUtils;
import com.redsponge.redengine.utils.Logger;

import java.lang.reflect.Field;

public class TransitionTextures {

    public final ShaderProgram mixShader;
    public final Texture slide;
    public final Texture dissolve;
    public final Texture circleOut;
    public final Texture circleIn;
//    public final Texture test;
    public Texture radial;

    private TransitionTextures() {
        mixShader = GeneralUtils.tryLoadShader(Gdx.files.internal("shaders/transition.vsh"), Gdx.files.internal("shaders/transition.fsh"));
        slide = new Texture("transition/slide.png");
        dissolve = new Texture("transition/dissolve.png");
        circleOut = new Texture("transition/circleOut.png");
        circleIn = new Texture("transition/circleIn.png");
        radial = new Texture("transition/radial.png");

//        test = new Texture("transition/test.png");
    }

    private static TransitionTextures instance;;

    public static TransitionTextures getInstance() {
        if(instance == null) {
            instance = new TransitionTextures();
        }
        return instance;
    }

    /**
     * Done so that there won't be a wait on the 1st time a transition is used because of just-in-time compiling
     */
    public void load() {
        Logger.log(this, "Loaded Transition Textures");
    }

    public void dispose() {
        Logger.log(this, "Disposing Transition Textures!");

        for (Field declaredField : instance.getClass().getDeclaredFields()) {
            if(Disposable.class.isAssignableFrom(declaredField.getType())) {
                try {
                    ((Disposable) declaredField.get(instance)).dispose();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void disposeInstance() {
        if(instance == null) {
            Logger.log(TransitionTextures.class, "Nothing To Dispose! Class Wasn't Needed!");
            return;
        }
        instance.dispose();
    }
}
