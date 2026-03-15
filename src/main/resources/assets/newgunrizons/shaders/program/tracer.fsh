#version 120

varying vec3 v_Normal;
varying vec3 v_EyePos;
varying float v_LengthPos;

uniform vec3 u_TracerColor;

void main() {
    vec3 N = normalize(v_Normal);
    vec3 V = normalize(-v_EyePos);

    // How directly this surface fragment faces the camera.
    // High on the cylinder strip nearest the viewer, low at the silhouette edges.
    // On the end cap facing the viewer this is uniformly high -> bright dot.
    float facing = abs(dot(N, V));

    // Hot white core blending into the tracer color at the edges
    vec3 color = mix(u_TracerColor, vec3(1.0), facing * facing);

    // Alpha: always somewhat visible, strongest when facing camera.
    // With additive blending and back-face rendering, the near and far sides
    // of the cylinder overlap and sum, giving a natural bright-center falloff.
    float alpha = mix(0.15, 0.6, facing);

    // Fade the tail end
    float lengthFade = smoothstep(0.0, 0.3, v_LengthPos);

    gl_FragColor = vec4(color * lengthFade, alpha * lengthFade);
}
