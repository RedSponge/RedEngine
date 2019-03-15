package com.redsponge.redengine.input;

public interface InputTranslator {

    /**
     * @return The horizontal status - from -1 to 1
     */
    float getHorizontal();

    /**
     * @return The vertical status - from -1 to 1
     */
    float getVertical();

    /**
     * @return Is jumping pressed?
     */
    boolean isJumping();

    /**
     * @return Is jumping just pressed?
     */
    boolean isJustJumping();

}
