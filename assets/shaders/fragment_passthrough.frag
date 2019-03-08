#ifdef GL_ES
precision mediump float;
#endif

varying v_color;
varying v_texCoords;

uniform sampler2D u_texture;

void main()
{
    vec4 color = texture2D(u_texture, v_texCoords);
    gl_FragColor = color;
}
