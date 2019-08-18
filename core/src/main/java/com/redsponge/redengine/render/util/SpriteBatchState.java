package com.redsponge.redengine.render.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

/**
 * Represents a state of the sprite batch
 * useful for things like changing blending/shaders/viewports etc.. for a moment and then switching back
 */
public class SpriteBatchState {

    private ShaderProgram shaderProgram;
    private Color color;

    private Matrix4 projectionMatrix;
    private Matrix4 transformMatrix;

    // C = Color, A = Alpha
    private int srcBlendFuncC;
    private int dstBlendFuncC;

    private int srcBlendFuncA;
    private int dstBlendFuncA;

    public SpriteBatchState(SpriteBatch batch) {
        extractState(batch);
    }

    public SpriteBatchState() {
        color = new Color();
    }

    /**
     * Applies a saved state to a batch
     * @param batch The batch to apply the state to
     * @return This for chaining
     */
    public SpriteBatchState applyState(SpriteBatch batch) {
        batch.setShader(shaderProgram);
        batch.setColor(color);
        batch.setProjectionMatrix(projectionMatrix);
        batch.setTransformMatrix(transformMatrix);

        batch.setBlendFunctionSeparate(srcBlendFuncC, dstBlendFuncC, srcBlendFuncA, dstBlendFuncA);

        return this;
    }

    /**
     * Extracts a batch's current state into this object
     * @param batch The batch to extract the state of
     * @return This for chaining
     */
    public SpriteBatchState extractState(SpriteBatch batch) {
        shaderProgram = batch.getShader();
        color.set(batch.getColor());
        projectionMatrix = batch.getProjectionMatrix();
        transformMatrix = batch.getTransformMatrix();

        srcBlendFuncC = batch.getBlendSrcFunc();
        dstBlendFuncC = batch.getBlendDstFunc();

        srcBlendFuncA = batch.getBlendSrcFuncAlpha();
        dstBlendFuncA = batch.getBlendDstFuncAlpha();

        return this;
    }

    /**********************************************
     * GETTERS AND SETTERS
     **********************************************/

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public void setShaderProgram(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Matrix4 getProjectionMatrix() {
        return projectionMatrix;
    }

    public void setProjectionMatrix(Matrix4 projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    public Matrix4 getTransformMatrix() {
        return transformMatrix;
    }

    public void setTransformMatrix(Matrix4 transformMatrix) {
        this.transformMatrix = transformMatrix;
    }

    public int getSrcBlendFuncC() {
        return srcBlendFuncC;
    }

    public void setSrcBlendFuncC(int srcBlendFuncC) {
        this.srcBlendFuncC = srcBlendFuncC;
    }

    public int getDstBlendFuncC() {
        return dstBlendFuncC;
    }

    public void setDstBlendFuncC(int dstBlendFuncC) {
        this.dstBlendFuncC = dstBlendFuncC;
    }

    public int getSrcBlendFuncA() {
        return srcBlendFuncA;
    }

    public void setSrcBlendFuncA(int srcBlendFuncA) {
        this.srcBlendFuncA = srcBlendFuncA;
    }

    public int getDstBlendFuncA() {
        return dstBlendFuncA;
    }

    public void setDstBlendFuncA(int dstBlendFuncA) {
        this.dstBlendFuncA = dstBlendFuncA;
    }
}
