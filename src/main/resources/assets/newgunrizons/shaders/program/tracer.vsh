#version 120

varying vec3 v_Normal;
varying vec3 v_EyePos;
varying float v_LengthPos;

void main() {
    v_EyePos = (gl_ModelViewMatrix * gl_Vertex).xyz;
    v_Normal = normalize(gl_NormalMatrix * gl_Normal);
    v_LengthPos = gl_Vertex.x;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}
