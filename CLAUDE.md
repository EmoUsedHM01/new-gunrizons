# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

NewGunrizons is a Minecraft 1.7.10 Forge mod built using the GT New Horizons (GTNH) build toolchain. It uses the `com.gtnewhorizons.gtnhconvention` Gradle plugin which provides standardized build configuration, RetroFuturaGradle integration, and CI/CD workflows.

**Mod ID:** `newgunrizons`
**Root package:** `com.gtnewhorizon.newgunrizons`
**Minecraft/Forge:** 1.7.10 / 10.13.4.1614

## Build Commands

```bash
# Build the mod jar
./gradlew build

# Run the Minecraft client (dev environment)
./gradlew runClient

# Run the Minecraft server (dev environment)
./gradlew runServer

# Run with a custom username
./gradlew runClient --username=PlayerName

# Apply code formatting (Spotless)
./gradlew spotlessApply

# Check code formatting without fixing
./gradlew spotlessCheck
```

## Architecture

### FML Lifecycle (Forge Mod Loader)

The mod uses the standard FML event-driven lifecycle via `@Mod.EventHandler` methods in `NewGunrizonsMod.java`:

1. **preInit** — Config loading, block/item registration with GameRegistry
2. **init** — Recipe registration, mod setup
3. **postInit** — Cross-mod compatibility

### Proxy Pattern

Client and server logic are separated via `@SidedProxy`:
- **CommonProxy** — Shared logic (config, registration). Always loaded.
- **ClientProxy** extends CommonProxy — Client-only logic (rendering, keybinds, GUIs). Only loaded on physical client.

The proxy is statically accessed via `NewGunrizonsMod.proxy`.

### Package Structure

```
com.gtnewhorizon.newgunrizons/
├── NewGunrizonsMod.java, CommonProxy, ClientProxy
├── config/          — ModContext, CommonModContext, ClientModContext, PlayerContext, SafeGlobals, Tags
├── weapon/          — Weapon, WeaponState, PlayerWeaponInstance, ItemAmmo, ItemBullet, etc.
├── attachment/      — AttachmentBuilder, ItemAttachment, ItemScope, CompatibleAttachment, etc.
├── grenade/         — ItemGrenade, EntityGrenade, GrenadeRenderer, etc.
├── entity/          — EntityProjectile, EntityShellCasing, WeaponSpawnEntity, Explosion
├── mechanic/        — WeaponFireAspect, WeaponReloadAspect, WeaponAttachmentAspect
├── state/           — Aspect, StateManager, RenderableState, Permit, etc.
├── network/         — All network messages + handlers, SyncManager, TypeRegistry
├── crafting/        — RecipeManager, RecipeGenerator, CraftingComplexity
├── client/
│   ├── render/      — WeaponRenderer, ShellCasingRenderer, RenderContext, etc.
│   ├── scope/       — ScopeManager, ScopeRenderer, ScopePerspective
│   ├── animation/   — Transition, MultipartTransition, IdleSway, etc.
│   ├── particle/    — FlashFX, SmokeFX, ExplosionParticleFX
│   ├── shader/      — DynamicShader, DynamicShaderGroup, Framebuffers
│   ├── input/       — KeyBindings, WeaponKeyInputHandler
│   ├── handler/     — ClientEventHandler, WeaponEventHandler, ClientWeaponTicker
│   ├── resource/    — WeaponResourcePack, TransformingResourceManager
│   └── effect/      — EffectManager
├── server/          — ServerEventHandler
├── registry/        — Guns, Attachments, AuxiliaryAttachments, Bullets, Magazines, etc.
├── tab/             — Creative tabs (GunsTab, AmmoTab, etc.)
├── block/           — Ore blocks
├── material/        — Item resources (ingots, plates) + electronic parts
├── factory/
│   ├── gun/{assault,sniper,smg,shotgun,pistol,lmg,special}/  — Gun factory files
│   └── grenade/     — Grenade factories
├── model/{weapon,action,sight,magazine,grip,ammo,misc}/       — 3D model files
├── mixin/           — MixinEntityRenderer, MixinRenderGlobal
└── util/            — MiscUtils, RayTracing, BlockPos, Tuple, etc.
```

## Key Build Conventions

- **Modern Java syntax** is enabled via Jabel — you can write Java 17 syntax (records, sealed classes, pattern matching, text blocks, etc.) while targeting JVM 8 bytecode.
- **Generic injection** is enabled — decompiled Minecraft source has proper generics (`List<E>`, `Map<K,V>`).
- **Dependencies** go in `dependencies.gradle` (Groovy DSL), not `build.gradle.kts`. See that file for available configurations (`api`, `implementation`, `compileOnly`, etc.).
- **Custom repositories** go in `repositories.gradle`. Well-known repos (CurseMaven, Modrinth) are included automatically.
- **Mixins** are disabled by default. Enable via `usesMixins = true` in `gradle.properties`.
- **Spotless** enforces code formatting. Run `./gradlew spotlessApply` before committing.
- **EditorConfig** is configured: 4-space indentation, UTF-8, LF line endings.
