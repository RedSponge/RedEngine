package com.redsponge.redengine.screen.components;

import com.badlogic.ashley.core.Component;

public class RenderRunnableComponent implements Component {

    private Runnable renderRunnable;

    public RenderRunnableComponent() {
    }

    public RenderRunnableComponent(Runnable renderRunnable) {
        this.renderRunnable = renderRunnable;
    }

    public Runnable getRenderRunnable() {
        return renderRunnable;
    }

    public RenderRunnableComponent setRenderRunnable(Runnable renderRunnable) {
        this.renderRunnable = renderRunnable;
        return this;
    }
}
