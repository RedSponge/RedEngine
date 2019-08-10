attribute vec2 a_texCoord0;
attribute vec3 a_position;
attribute vec4 a_color;

varying vec2 v_texCoords;
varying vec4 v_color;

uniform mat4 u_projTrans;

void main() {
    v_color = a_color;
    v_texCoords = a_texCoord0;

    gl_Position = u_projTrans * vec4(a_position, 1.0);
}