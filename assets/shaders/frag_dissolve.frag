#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;
varying vec4 v_color;
uniform sampler2D u_texture;

uniform float u_progress;

void main()
{
    vec4 color = texture2D(u_texture, v_texCoords);

    vec4 onColor = vec4(1, 1, 1, 0);
    vec4 offColor = vec4(0, 0, 0, 1);

    vec4 outColor = color.r < u_progress ? offColor : onColor;

    gl_FragColor = outColor;
}