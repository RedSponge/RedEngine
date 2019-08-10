#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;
varying vec4 v_color;

uniform sampler2D u_texture; // black
uniform sampler2D white;
uniform sampler2D mask;

void main() {
    vec4 blackC = texture2D(u_texture, v_texCoords);
    vec4 whiteC = texture2D(white, v_texCoords);
    vec4 maskC = texture2D(mask, v_texCoords);

    gl_FragColor = blackC;//v_color * mix(blackC, whiteC, maskC);
}