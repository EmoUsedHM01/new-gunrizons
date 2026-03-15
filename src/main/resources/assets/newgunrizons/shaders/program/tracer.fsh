#version 120

varying vec3 v_Normal;
varying vec3 v_EyePos;
varying float v_LengthPos;

uniform vec3 u_TracerColor;
uniform float u_TracerLength;
uniform float u_Intensity;

void main() {
    vec3 N = normalize(v_Normal);
    vec3 V = normalize(-v_EyePos);

    float facing = abs(dot(N, V));

    // Core color: white-hot center blending to tracer color at edges.
    // At high intensity, the entire beam trends toward white.
    float coreBlend = facing * facing * min(u_Intensity, 1.5);
    vec3 color = mix(u_TracerColor, vec3(1.0), clamp(coreBlend, 0.0, 1.0));

    // Brightness: intensity multiplies the final color (additive blending
    // means brighter color = more visible glow).
    color *= u_Intensity;

    // Alpha: at intensity 1 the beam fades at glancing angles (normal tracer).
    // At intensity 3+ the beam is nearly opaque from every angle (solid laser).
    float minAlpha = clamp(0.15 * u_Intensity, 0.15, 0.9);
    float maxAlpha = clamp(0.6 * u_Intensity, 0.6, 1.0);
    float alpha = mix(minAlpha, maxAlpha, facing);

    // Fade the tail over a fixed 1.5-unit distance.
    float fadeZone = clamp(1.5 / u_TracerLength, 0.02, 0.4);
    float lengthFade = smoothstep(0.0, fadeZone, v_LengthPos);

    gl_FragColor = vec4(color * lengthFade, alpha * lengthFade);
}
