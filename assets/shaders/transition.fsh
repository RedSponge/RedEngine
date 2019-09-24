#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

uniform float u_progress;  // The transition's progress

uniform vec4 blackColor;
uniform vec4 whiteColor;

uniform bool flipped;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords);

    if(flipped) {
        color.rgb = vec3(1.0) - color.rgb;
    }

    vec4 outColor = u_progress < color.r ? whiteColor : blackColor;

    gl_FragColor = outColor * color * v_color;
}
