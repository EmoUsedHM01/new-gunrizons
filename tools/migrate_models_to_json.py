#!/usr/bin/env python3
"""
Migrates all model class instantiations in factory/registry/renderer files
from `new ModelClassName()` to `new JsonModel("category/modelname")`.

Updates both the constructor calls and the import statements.
"""

import os
import re
import glob

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
MODEL_SRC = os.path.join(ROOT, "src", "main", "java", "com", "gtnewhorizon", "newgunrizons", "model")
JAVA_SRC = os.path.join(ROOT, "src", "main", "java", "com", "gtnewhorizon", "newgunrizons")

# Build mapping: ClassName -> ("category/lowercase", "original.package")
def build_class_map():
    class_map = {}

    # Scan subdirectories
    for category in ["weapon", "action", "sight", "magazine", "grip", "ammo", "misc"]:
        cat_dir = os.path.join(MODEL_SRC, category)
        if not os.path.isdir(cat_dir):
            continue
        for f in os.listdir(cat_dir):
            if f.endswith(".java"):
                class_name = f[:-5]  # strip .java
                lower = class_name.lower()
                package = f"com.gtnewhorizon.newgunrizons.model.{category}.{class_name}"
                class_map[class_name] = (f"{category}/{lower}", package)

    # Root-level models
    for f in ["ModelShell.java", "ModelBullet.java"]:
        path = os.path.join(MODEL_SRC, f)
        if os.path.exists(path):
            class_name = f[:-5]
            lower = class_name.lower()
            package = f"com.gtnewhorizon.newgunrizons.model.{class_name}"
            class_map[class_name] = (f"misc/{lower}", package)

    return class_map


def process_file(filepath, class_map):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    original = content
    json_model_import = "import com.gtnewhorizon.newgunrizons.model.JsonModel;"
    needs_json_import = False
    imports_to_remove = set()

    # Find all model instantiations: new ClassName()
    # Match both `new ClassName()` patterns
    for class_name, (geo_path, package) in class_map.items():
        # Pattern: new ClassName() - must be a standalone instantiation
        pattern = re.compile(r'\bnew\s+' + re.escape(class_name) + r'\s*\(\s*\)')
        if pattern.search(content):
            replacement = f'new JsonModel("{geo_path}")'
            content = pattern.sub(replacement, content)
            needs_json_import = True
            imports_to_remove.add(package)

    if content == original:
        return False  # No changes

    # Remove old model imports
    lines = content.split('\n')
    new_lines = []
    json_import_exists = False

    for line in lines:
        stripped = line.strip()

        # Check if this line is an import we should remove
        should_remove = False
        for pkg in imports_to_remove:
            if stripped == f"import {pkg};":
                should_remove = True
                break

        # Also remove wildcard imports for model subpackages if all models from that
        # package are being replaced (we'll keep them since some might still be used)
        # Actually, just remove the specific imports

        if should_remove:
            continue

        if stripped == json_model_import:
            json_import_exists = True

        new_lines.append(line)

    # Add JsonModel import if needed and not already present
    if needs_json_import and not json_import_exists:
        # Find the right place to insert (after last import in the model package)
        insert_idx = None
        for i, line in enumerate(new_lines):
            stripped = line.strip()
            if stripped.startswith("import com.gtnewhorizon.newgunrizons.model."):
                insert_idx = i + 1
            elif stripped.startswith("import ") and insert_idx is not None:
                # We've passed the model imports section
                break

        if insert_idx is None:
            # Find last import line
            for i, line in enumerate(new_lines):
                if line.strip().startswith("import "):
                    insert_idx = i + 1

        if insert_idx is not None:
            new_lines.insert(insert_idx, json_model_import)

    content = '\n'.join(new_lines)

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

    return True


def main():
    class_map = build_class_map()
    print(f"Built mapping for {len(class_map)} model classes")

    # Collect all Java files to process
    search_dirs = [
        os.path.join(JAVA_SRC, "items", "factories"),
        os.path.join(JAVA_SRC, "registry"),
        os.path.join(JAVA_SRC, "client", "render"),
        os.path.join(JAVA_SRC, "client", "handler"),
        os.path.join(JAVA_SRC, "entity"),
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
