#!/usr/bin/env python3
"""
Converts legacy Minecraft 1.7.10 Java ModelBase/ModelRenderer classes
to Bedrock geometry JSON (.geo.json) format, importable by Blockbench.

Usage:
    python java_model_to_geo.py <input.java> [output.geo.json]

Handles the pattern:
    this.partName = new ModelRenderer(this, texU, texV);
    this.partName.addBox(offX, offY, offZ, sizeX, sizeY, sizeZ);
    this.partName.setRotationPoint(pivotX, pivotY, pivotZ);
    this.partName.setTextureSize(texW, texH);
    this.partName.mirror = true/false;
    this.setRotation(this.partName, rotX, rotY, rotZ);

Coordinate conversion (Java entity model -> Bedrock geometry):
  - Java ModelRenderer Y-axis is inverted (positive = down)
  - Bedrock geometry Y-axis is up, with 0 at ground
  - Standard 24-unit offset for entity height is applied

Output: Bedrock geometry format_version 1.12.0 (widely compatible)
"""

import re
import sys
import json
import math
import os


def parse_java_model(java_source):
    """Parse a Java ModelBase source file and extract all ModelRenderer definitions."""

    # Extract texture dimensions from constructor
    tex_width = 64
    tex_height = 32
    m = re.search(r'this\.textureWidth\s*=\s*(\d+)', java_source)
    if m:
        tex_width = int(m.group(1))
    m = re.search(r'this\.textureHeight\s*=\s*(\d+)', java_source)
    if m:
        tex_height = int(m.group(1))

    parts = {}

    # Find all ModelRenderer constructor calls: new ModelRenderer(this, u, v)
    for m in re.finditer(
        r'this\.(\w+)\s*=\s*new\s+ModelRenderer\s*\(\s*this\s*,\s*(-?[\d.]+)\s*,\s*(-?[\d.]+)\s*\)',
        java_source
    ):
        name = m.group(1)
        parts[name] = {
            'name': name,
            'uv': [int(float(m.group(2))), int(float(m.group(3)))],
            'offset': [0, 0, 0],
            'size': [1, 1, 1],
            'pivot': [0, 0, 0],
            'rotation': [0, 0, 0],
            'mirror': False,
        }

    # Parse addBox calls
    for m in re.finditer(
        r'this\.(\w+)\.addBox\s*\(\s*'
        r'(-?[\d.]+)F?\s*,\s*(-?[\d.]+)F?\s*,\s*(-?[\d.]+)F?\s*,\s*'
        r'(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*\)',
        java_source
    ):
        name = m.group(1)
        if name in parts:
            parts[name]['offset'] = [float(m.group(2)), float(m.group(3)), float(m.group(4))]
            parts[name]['size'] = [int(m.group(5)), int(m.group(6)), int(m.group(7))]

    # Parse setRotationPoint calls
    for m in re.finditer(
        r'this\.(\w+)\.setRotationPoint\s*\(\s*'
        r'(-?[\d.]+)F?\s*,\s*(-?[\d.]+)F?\s*,\s*(-?[\d.]+)F?\s*\)',
        java_source
    ):
        name = m.group(1)
        if name in parts:
            parts[name]['pivot'] = [float(m.group(2)), float(m.group(3)), float(m.group(4))]

    # Parse setRotation calls
    for m in re.finditer(
        r'this\.setRotation\s*\(\s*this\.(\w+)\s*,\s*'
        r'(-?[\d.]+)F?\s*,\s*(-?[\d.]+)F?\s*,\s*(-?[\d.]+)F?\s*\)',
        java_source
    ):
        name = m.group(1)
        if name in parts:
            parts[name]['rotation'] = [float(m.group(2)), float(m.group(3)), float(m.group(4))]

    # Parse mirror
    for m in re.finditer(r'this\.(\w+)\.mirror\s*=\s*(true|false)', java_source):
        name = m.group(1)
        if name in parts:
            parts[name]['mirror'] = m.group(2) == 'true'

    return tex_width, tex_height, parts


def convert_to_bedrock_geo(tex_width, tex_height, parts, identifier="geometry.model"):
    """
    Convert parsed Java model parts to Bedrock geometry format.

    Coordinate conversion:
      Java: Y+ is down, origin at entity center
      Bedrock: Y+ is up, origin at ground (Y=0)

      For each cube:
        bedrock_pivot = [java_pivot_x, 24 - java_pivot_y, java_pivot_z]
        bedrock_origin = [java_pivot_x + offset_x,
                          24 - java_pivot_y - offset_y - size_y,
                          java_pivot_z + offset_z]
        bedrock_rotation = [-deg(java_rot_x), -deg(java_rot_y), deg(java_rot_z)]

    Since Bedrock geometry format 1.12.0 only supports rotation on bones (not cubes),
    each rotated part becomes its own bone. Non-rotated parts are grouped together.
    """

    bones = []
    unrotated_cubes = []

    # Sort parts by name for deterministic output
    sorted_parts = sorted(parts.values(), key=lambda p: _natural_sort_key(p['name']))

    for part in sorted_parts:
        px, py, pz = part['pivot']
        ox, oy, oz = part['offset']
        sx, sy, sz = part['size']
        rx, ry, rz = part['rotation']

        # Convert coordinates
        bedrock_pivot = [px, 24 - py, pz]
        bedrock_origin = [
            px + ox,
            24 - py - oy - sy,
            pz + oz,
        ]

        cube = {
            "origin": _round_list(bedrock_origin),
            "size": [sx, sy, sz],
            "uv": part['uv'],
        }
        if part['mirror']:
            cube["mirror"] = True

        has_rotation = (rx != 0 or ry != 0 or rz != 0)

        if has_rotation:
            # Each rotated part needs its own bone
            bone = {
                "name": part['name'],
                "pivot": _round_list(bedrock_pivot),
                "rotation": _round_list([
                    -math.degrees(rx),
                    -math.degrees(ry),
                    math.degrees(rz),
                ]),
                "cubes": [cube],
            }
            bones.append(bone)
        else:
            # Collect non-rotated cubes into a single root bone
            unrotated_cubes.append(cube)

    # Add root bone with all non-rotated cubes
    if unrotated_cubes:
        bones.insert(0, {
            "name": "root",
            "pivot": [0, 0, 0],
            "cubes": unrotated_cubes,
        })

    geo = {
        "format_version": "1.12.0",
        "minecraft:geometry": [
            {
                "description": {
                    "identifier": identifier,
                    "texture_width": tex_width,
                    "texture_height": tex_height,
                    "visible_bounds_width": 4,
                    "visible_bounds_height": 4,
                    "visible_bounds_offset": [0, 1, 0],
                },
                "bones": bones,
            }
        ],
    }

    return geo


def _round_list(values, precision=4):
    """Round floating point values, converting to int if whole."""
    result = []
    for v in values:
        r = round(v, precision)
        if r == int(r):
            r = int(r)
        result.append(r)
    return result


def _natural_sort_key(name):
    """Sort strings with embedded numbers naturally (gun2 < gun10)."""
    parts = re.split(r'(\d+)', name)
    result = []
    for p in parts:
        if p.isdigit():
            result.append(int(p))
        else:
            result.append(p.lower())
    return result


def main():
    if len(sys.argv) < 2:
        print("Usage: python java_model_to_geo.py <input.java> [output.geo.json]")
        print("  Converts a 1.7.10 Java entity model class to Bedrock geometry JSON.")
        sys.exit(1)

    input_path = sys.argv[1]
    base_name = os.path.splitext(os.path.basename(input_path))[0].lower()

    if len(sys.argv) >= 3:
        output_path = sys.argv[2]
    else:
        output_path = os.path.join(os.path.dirname(input_path), f"{base_name}.geo.json")

    with open(input_path, 'r', encoding='utf-8') as f:
        java_source = f.read()

    tex_width, tex_height, parts = parse_java_model(java_source)

    print(f"Parsed {len(parts)} parts from {input_path}")
    print(f"Texture size: {tex_width}x{tex_height}")

    identifier = f"geometry.{base_name}"
    geo = convert_to_bedrock_geo(tex_width, tex_height, parts, identifier)

    bone_count = len(geo['minecraft:geometry'][0]['bones'])
    cube_count = sum(len(b.get('cubes', [])) for b in geo['minecraft:geometry'][0]['bones'])
    print(f"Output: {bone_count} bones, {cube_count} cubes")

    with open(output_path, 'w', encoding='utf-8') as f:
        json.dump(geo, f, indent=2)

    print(f"Written to {output_path}")


if __name__ == '__main__':
    main()
