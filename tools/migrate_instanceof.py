#!/usr/bin/env python3
"""
Replaces `model instanceof ModelClassName` with `JsonModel.is(model, "category/classname")`
and cleans up stale model imports.
"""

import os
import re

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
MODEL_SRC = os.path.join(ROOT, "src", "main", "java", "com", "gtnewhorizon", "newgunrizons", "model")
JAVA_SRC = os.path.join(ROOT, "src", "main", "java", "com", "gtnewhorizon", "newgunrizons")

MODEL_PACKAGES = ["weapon", "action", "sight", "magazine", "grip", "ammo", "misc"]


def build_class_map():
    """Map class names to their geo.json path and full package."""
    class_map = {}

    for category in MODEL_PACKAGES:
        cat_dir = os.path.join(MODEL_SRC, category)
        if not os.path.isdir(cat_dir):
            continue
        for f in os.listdir(cat_dir):
            if f.endswith(".java"):
                class_name = f[:-5]
                lower = class_name.lower()
                package = f"com.gtnewhorizon.newgunrizons.model.{category}.{class_name}"
                class_map[class_name] = (f"{category}/{lower}", package)

    # Root-level models
    for f in ["ModelShell.java", "ModelBullet.java"]:
        if os.path.exists(os.path.join(MODEL_SRC, f)):
            class_name = f[:-5]
            lower = class_name.lower()
            package = f"com.gtnewhorizon.newgunrizons.model.{class_name}"
            class_map[class_name] = (f"misc/{lower}", package)

    return class_map


def process_file(filepath, class_map):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    original = content
    imports_to_remove = set()
    needs_json_import = False

    # Replace instanceof checks: `model instanceof ClassName`
    # Sort by longest name first to avoid partial matches
    for class_name in sorted(class_map.keys(), key=len, reverse=True):
        geo_path, package = class_map[class_name]

        # Pattern: variable instanceof ClassName
        # Must match word boundaries to avoid partial matches like ACRAction matching ACRAction2
        pattern = re.compile(r'(\w+)\s+instanceof\s+' + re.escape(class_name) + r'(?![A-Za-z0-9_])')

        if pattern.search(content):
            def replacement(m):
                var_name = m.group(1)
                return f'JsonModel.is({var_name}, "{geo_path}")'

            content = pattern.sub(replacement, content)
            imports_to_remove.add(package)
            needs_json_import = True

    if content == original:
        return False

    # Clean up imports
    lines = content.split('\n')
    new_lines = []
    json_import_exists = False

    # Also check for JsonModel import
    for line in lines:
        stripped = line.strip()

        should_remove = False
        for pkg in imports_to_remove:
            if stripped == f"import {pkg};":
                should_remove = True
                break

        if should_remove:
            continue

        if stripped == "import com.gtnewhorizon.newgunrizons.model.BedrockModel;":
            json_import_exists = True

        new_lines.append(line)

    # Add JsonModel import if needed
    if needs_json_import and not json_import_exists:
        insert_idx = None
        for i, line in enumerate(new_lines):
            stripped = line.strip()
            if stripped.startswith("import com.gtnewhorizon.newgunrizons."):
                insert_idx = i + 1
        if insert_idx is None:
            for i, line in enumerate(new_lines):
                if line.strip().startswith("import "):
                    insert_idx = i + 1
        if insert_idx is not None:
            new_lines.insert(insert_idx, "import com.gtnewhorizon.newgunrizons.model.BedrockModel;")

    content = '\n'.join(new_lines)

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

    return True


def main():
    class_map = build_class_map()
    print(f"Built mapping for {len(class_map)} model classes")

    search_dirs = [
        os.path.join(JAVA_SRC, "items", "factories"),
        os.path.join(JAVA_SRC, "registry"),
        os.path.join(JAVA_SRC, "client"),
        os.path.join(JAVA_SRC, "entity"),
        os.path.join(JAVA_SRC, "server"),
    ]

    java_files = []
    for d in search_dirs:
        if os.path.isdir(d):
            for root, dirs, files in os.walk(d):
                for f in files:
                    if f.endswith(".java"):
                        java_files.append(os.path.join(root, f))

    print(f"Scanning {len(java_files)} Java files...")

    modified = 0
    for filepath in sorted(java_files):
        if process_file(filepath, class_map):
            rel = os.path.relpath(filepath, ROOT)
            print(f"  Modified: {rel}")
            modified += 1

    print(f"\nDone: {modified} files modified")


if __name__ == '__main__':
    main()
