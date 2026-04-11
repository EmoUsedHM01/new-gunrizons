# NewGunrizons

A Minecraft 1.7.10 firearms mod for the [GT New Horizons](https://github.com/GTNewHorizons) modpack. NewGunrizons is a heavily modified fork of [**Vic's Modern Warfare**](https://github.com/vic4games/modern-warfare) (also known as Modern Warfare Mod / MWC), rebuilt from the ground up to integrate with GregTech and the GTNH ecosystem.

> **WARNING: This mod is built for GTNH 2.8.4 release.**
> It depends on specific versions of GT5-Unofficial (5.09.51.482), Angelica (1.0.0-beta66b), and GTNHLib (0.7.10) shipped with that release. It is **not guaranteed to work** with newer or older GTNH versions, as internal APIs and class structures may change between releases.

## Shader Compatibility

NewGunrizons should be compatible with Angelica's shader pipeline. Muzzle flash effects use Angelica's dynamic lighting system for real-time illumination when firing. Scope rendering works correctly with both shaders enabled and disabled.

## What Changed from the Original De-make

This fork builds on [vegeta1k95's NewGunrizons](https://github.com/vegeta1k95/NewGunrizons), a de-make of [Vic's Modern Warfare](https://github.com/vic4games/modern-warfare) for 1.7.10. The original de-make restructured and refactored the codebase, cut everything unrelated to weapons, migrated to JSON/Bedrock models, added bullet tracers with Iris/shader compatibility, and integrated GregTech crafting with tiered progression.

This fork adds the following on top of that work:

### Animation & Feel
- **Smooth ADS transitions** - aiming down sights now reverses smoothly from the current animation position instead of snapping
- **Weapon sway** - guns now have a subtle idle sway while held, with more aggressive vertical shaking while sprinting
- **Configurable ADS** - aim-down-sights zoom, sensitivity, and timing are exposed via config

### Enchantment System
Eight gun-specific enchantments that can be applied via enchanting table or anvil:

| Enchantment | Max Level | Effect |
|-------------|-----------|--------|
| Armor Piercing | III | 25% of damage per level bypasses armor |
| Collateral | I | Bullets pass through entities, hitting multiple targets |
| Ethereal Rounds | IV | 15% chance per level to not consume ammo |
| Fast Hands | III | Reduces reload time by 20% per level (animation + timing) |
| Hollow Point | V | Increases bullet damage by 0.5 + 0.5 per level |
| Incendiary | I | Sets targets on fire; ignites blocks on impact |
| Knockback | II | Knocks targets back along bullet trajectory |
| Stability | IV | Reduces recoil by 20% per level |

- Dynamic enchantment ID allocation to avoid conflicts in heavily modded environments
- Enchantment descriptions visible via WAWLA (hold sneak on enchanted books)
- Hollow Point bonus damage reflected in weapon tooltip

### Combat
- **Headshot system** - bullets that hit the top 25% of an entity's bounding box deal double damage

### Tooltips & UI
- Enchantments display at the top of weapon tooltips (matching vanilla item style)
- Attachments show "Can be attached to: ..." listing all compatible guns
- Hollow Point dynamically modifies the damage line in weapon tooltips

## Features

### Weapons

Over **100 firearms** spanning multiple eras and categories:

| Category | Count | Examples |
|----------|-------|---------|
| Assault Rifles & Carbines | 33 | AK-47, M4A1, G36, AUG, SCAR-H, AN-94 |
| Pistols & Machine Pistols | 20 | M1911, Glock-18, Desert Eagle, Luger P08, Makarov PM |
| SMGs & PDWs | 18 | MP5, P90, KRISS Vector, PPSh-41, MP-40 |
| Sniper & Marksman Rifles | 21 | AWP, Dragunov SVD, Barrett M107, Kar98K, Mosin Nagant |
| Shotguns & Heavy Weapons | 12 | Remington 870, SPAS-12, Saiga 12, M249 SAW, Mk153 SMAW |


### Attachments

- **Scopes & Sights** — ACOG, EOTech holographic, Reflex, Kobra, Leupold, PSO-1, OKP-7, PU scope, and more
- **Suppressors** — caliber-specific silencers for 9mm through .50 BMG
- **Grips** — angled grip, stubby grip, vertical foregrip, bipod
- **Lasers** — tactical laser sights


### Ammunition System

- **Realistic caliber system** — each weapon uses its specific ammunition type
- **Magazine-based reloading** — most weapons use detachable magazines that must be loaded with the correct bullet type
- **Three casing tiers** — small (pistol/SMG), medium (rifle), and large (.50 BMG) casings as crafting intermediates


### GregTech Integration

All crafting is done through GregTech machines.

**Component crafting:**
- **Assembler** — gun barrels (plate + stick), stocks (wood/plastic/carbon), precision lenses, scopes, grips, suppressors, ammo, magazines, gun assembly
- **Forming Press** — receivers (plates), bullet casings (brass/steel), firing mechanisms, weapon part kits

**Weapon tiers:**

| Tier | EU/t | Components | Example Weapons |
|------|------|------------|-----------------|
| LV (30) | Steel barrel + Steel receiver + Wood stock | Luger P08, Kar98K, M1 Garand, STG-44 |
| MV (120) | Stainless barrel + Steel receiver + Plastic stock | MP-40, Glock-18, Desert Eagle, Remington 870 |
| HV (480) | Titanium barrel + Stainless receiver + Plastic stock | AK-47, M4A1, MP5, Dragunov |
| EV (1920) | TungstenSteel barrel + Titanium receiver + Carbon stock | SCAR-H, AK-12, Barrett M107, M249 |
| IV (7680) | TungstenSteel barrel + Titanium receiver + Carbon stock | M41A, SMAW |


## Building

```bash
# Build the mod jar
./gradlew build

# Output jar will be in build/libs/
```

## Controls

| Key         | Action |
|-------------|--------|
| Right Click | Aim Down Sights |
| Left Click  | Fire |
| R           | Reload |
| R-Shift     | Selective Fire (Semi/Burst/Auto) |
| M           | Attachment Mode |
| L           | Switch Laser |
| ] / [       | Zoom In / Out (while scoped) |

## Credits

- **Original mod**: [Vic's Modern Warfare](https://github.com/vic4games/modern-warfare) by Vic4Games — the foundation this mod was built upon
- **De-make**: [NewGunrizons](https://github.com/vegeta1k95/NewGunrizons) by vegeta1k95 — the 1.7.10 port this fork is based on
- **GT New Horizons team** — for the modpack and build toolchain

## License

This mod is a derivative work of Vic's Modern Warfare. Please refer to the original mod's license terms for redistribution and modification rights.
