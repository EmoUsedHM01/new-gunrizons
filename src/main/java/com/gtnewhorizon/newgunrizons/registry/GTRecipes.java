package com.gtnewhorizon.newgunrizons.registry;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import cpw.mods.fml.common.Loader;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class GTRecipes {
   public static void init() {
      if (Loader.isModLoaded("gregtech")) {
         addComponentRecipes();
         addGunRecipes();
         addAmmoRecipes();
         addMagazineRecipes();
         addAttachmentRecipes();
         addGrenadeRecipes();
      }
   }

   private static void addComponentRecipes() {
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2L),
               GTOreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 2L),
               GTUtility.getIntegratedCircuit(3)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunBarrelSteel)})
         .duration(200)
         .eut(30)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 2L),
               GTOreDictUnificator.get(OrePrefixes.stick, Materials.StainlessSteel, 2L),
               GTUtility.getIntegratedCircuit(3)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunBarrelStainless)})
         .duration(200)
         .eut(120)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 2L),
               GTOreDictUnificator.get(OrePrefixes.stick, Materials.Titanium, 2L),
               GTUtility.getIntegratedCircuit(3)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunBarrelTitanium)})
         .duration(300)
         .eut(480)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 2L),
               GTOreDictUnificator.get(OrePrefixes.stick, Materials.TungstenSteel, 2L),
               GTUtility.getIntegratedCircuit(3)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunBarrelTungstenSteel)})
         .duration(400)
         .eut(1920)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(new ItemStack[]{GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 4L), GTUtility.getIntegratedCircuit(3)})
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunReceiverSteel)})
         .duration(200)
         .eut(30)
         .addTo(RecipeMaps.formingPressRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(new ItemStack[]{GTOreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 4L), GTUtility.getIntegratedCircuit(3)})
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunReceiverStainless)})
         .duration(200)
         .eut(120)
         .addTo(RecipeMaps.formingPressRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(new ItemStack[]{GTOreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 4L), GTUtility.getIntegratedCircuit(3)})
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunReceiverTitanium)})
         .duration(300)
         .eut(480)
         .addTo(RecipeMaps.formingPressRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(new ItemStack[]{GTOreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 4L), GTUtility.getIntegratedCircuit(3)})
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunReceiverTungstenSteel)})
         .duration(400)
         .eut(1920)
         .addTo(RecipeMaps.formingPressRecipes);
      ItemStack smallCasing = new ItemStack(CommonProxy.BulletCasingSmall, 16);
      GTValues.RA
         .stdBuilder()
         .itemInputs(new ItemStack[]{GTOreDictUnificator.get(OrePrefixes.plate, Materials.Brass, 2L), GTUtility.getIntegratedCircuit(4)})
         .itemOutputs(new ItemStack[]{smallCasing})
         .duration(100)
         .eut(30)
         .addTo(RecipeMaps.formingPressRecipes);
      ItemStack mediumCasing = new ItemStack(CommonProxy.BulletCasingMedium, 8);
      GTValues.RA
         .stdBuilder()
         .itemInputs(new ItemStack[]{GTOreDictUnificator.get(OrePrefixes.plate, Materials.Brass, 2L), GTUtility.getIntegratedCircuit(5)})
         .itemOutputs(new ItemStack[]{mediumCasing})
         .duration(100)
         .eut(30)
         .addTo(RecipeMaps.formingPressRecipes);
      ItemStack largeCasing = new ItemStack(CommonProxy.BulletCasingLarge, 4);
      GTValues.RA
         .stdBuilder()
         .itemInputs(new ItemStack[]{GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2L), GTUtility.getIntegratedCircuit(6)})
         .itemOutputs(new ItemStack[]{largeCasing})
         .duration(100)
         .eut(120)
         .addTo(RecipeMaps.formingPressRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.spring, Materials.Steel, 2L),
               GTOreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Steel, 1L),
               GTOreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 1L),
               GTUtility.getIntegratedCircuit(1)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.FiringMechanism)})
         .duration(200)
         .eut(30)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.spring, Materials.Titanium, 2L),
               GTOreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Titanium, 1L),
               GTOreDictUnificator.get(OrePrefixes.stick, Materials.Titanium, 1L),
               GTUtility.getIntegratedCircuit(1)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.FiringMechanismAdvanced)})
         .duration(300)
         .eut(480)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(new ItemStack[]{GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 1L), GTUtility.getIntegratedCircuit(2)})
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.WeaponPartKit, 4)})
         .duration(100)
         .eut(30)
         .addTo(RecipeMaps.formingPressRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.plank, Materials.Wood, 4L),
               GTOreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 2L),
               GTUtility.getIntegratedCircuit(1)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunStockWood)})
         .duration(200)
         .eut(30)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.plate, Materials.Plastic, 4L),
               GTOreDictUnificator.get(OrePrefixes.screw, Materials.Aluminium, 2L),
               GTUtility.getIntegratedCircuit(1)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunStockPlastic)})
         .duration(200)
         .eut(120)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.plate, Materials.Carbon, 2L),
               GTOreDictUnificator.get(OrePrefixes.screw, Materials.Titanium, 2L),
               GTUtility.getIntegratedCircuit(1)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.GunStockCarbon)})
         .duration(200)
         .eut(480)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               GTOreDictUnificator.get(OrePrefixes.lens, Materials.Glass, 2L),
               GTOreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 4L),
               GTUtility.getIntegratedCircuit(2)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(CommonProxy.PrecisionLens)})
         .duration(200)
         .eut(120)
         .addTo(RecipeMaps.assemblerRecipes);
   }

   private static void addGunRecipes() {
      int lvEut = 30;
      int lvDur = 200;
      addLVGun(Guns.LugerP08, 1, lvEut, lvDur);
      addLVGun(Guns.P08Artillerie, 2, lvEut, lvDur);
      addLVGun(Guns.M1911, 3, lvEut, lvDur);
      addLVGun(Guns.Webley, 4, lvEut, lvDur);
      addLVGun(Guns.EnfieldNo2, 5, lvEut, lvDur);
      addLVGun(Guns.Kar98K, 6, lvEut, lvDur);
      addLVGun(Guns.MosinNagant, 7, lvEut, lvDur);
      addLVGun(Guns.MosinPU, 8, lvEut, lvDur);
      addLVGun(Guns.LeeEnfieldSMLE, 9, lvEut, lvDur);
      addLVGun(Guns.LeeEnfieldNo4MkI, 10, lvEut, lvDur);
      addLVGun(Guns.Gewehr98, 11, lvEut, lvDur);
      addLVGun(Guns.STG44, 12, lvEut, lvDur);
      addLVGun(Guns.MP18, 13, lvEut, lvDur);
      addLVGun(Guns.M1Garand, 14, lvEut, lvDur);
      addLVGun(Guns.M1903A1, 15, lvEut, lvDur);
      addLVGun(Guns.M1903A3, 16, lvEut, lvDur);
      addLVGun(Guns.M1Carbine, 17, lvEut, lvDur);
      int mvEut = 120;
      int mvDur = 300;
      addMVGun(Guns.MP40, 1, mvEut, mvDur);
      addMVGun(Guns.MP41, 2, mvEut, mvDur);
      addMVGun(Guns.M1928Thompson, 3, mvEut, mvDur);
      addMVGun(Guns.M1A1Thompson, 4, mvEut, mvDur);
      addMVGun(Guns.PPSh41, 5, mvEut, mvDur);
      addMVGun(Guns.Type100, 6, mvEut, mvDur);
      addMVGun(Guns.M3A1, 7, mvEut, mvDur);
      addMVGun(Guns.Glock18, 8, mvEut, mvDur);
      addMVGun(Guns.G21, 9, mvEut, mvDur);
      addMVGun(Guns.Glock32, 10, mvEut, mvDur);
      addMVGun(Guns.M9, 11, mvEut, mvDur);
      addMVGun(Guns.USP45, 12, mvEut, mvDur);
      addMVGun(Guns.P2000, 13, mvEut, mvDur);
      addMVGun(Guns.P226, 14, mvEut, mvDur);
      addMVGun(Guns.P99, 15, mvEut, mvDur);
      addMVGun(Guns.Deagle44, 16, mvEut, mvDur);
      addMVGun(Guns.Python, 17, mvEut, mvDur);
      addMVGun(Guns.MakarovPM, 18, mvEut, mvDur);
      addMVGun(Guns.M1911Taurus, 19, mvEut, mvDur);
      addMVGun(Guns.Remington870, 20, mvEut, mvDur);
      addMVGun(Guns.SPAS12, 21, mvEut, mvDur);
      addMVGun(Guns.M2Carbine, 22, mvEut, mvDur);
      addMVGun(Guns.SVT40, 23, mvEut, mvDur);
      addMVGun(Guns.Tec9, 24, mvEut, mvDur);
      int hvEut = 480;
      int hvDur = 400;
      addHVGun(Guns.AK47, 1, hvEut, hvDur);
      addHVGun(Guns.AKM, 2, hvEut, hvDur);
      addHVGun(Guns.AK74U, 3, hvEut, hvDur);
      addHVGun(Guns.AK74M, 4, hvEut, hvDur);
      addHVGun(Guns.M4A1, 5, hvEut, hvDur);
      addHVGun(Guns.M16A1, 6, hvEut, hvDur);
      addHVGun(Guns.M16A4, 7, hvEut, hvDur);
      addHVGun(Guns.G36, 8, hvEut, hvDur);
      addHVGun(Guns.G36C, 9, hvEut, hvDur);
      addHVGun(Guns.AUG, 10, hvEut, hvDur);
      addHVGun(Guns.AUGA3, 11, hvEut, hvDur);
      addHVGun(Guns.Famas, 12, hvEut, hvDur);
      addHVGun(Guns.FamasF1, 13, hvEut, hvDur);
      addHVGun(Guns.FNFAL, 14, hvEut, hvDur);
      addHVGun(Guns.MP5, 15, hvEut, hvDur);
      addHVGun(Guns.MP5K, 16, hvEut, hvDur);
      addHVGun(Guns.MP7, 17, hvEut, hvDur);
      addHVGun(Guns.FNP90, 18, hvEut, hvDur);
      addHVGun(Guns.KrissVector, 19, hvEut, hvDur);
      addHVGun(Guns.MAC10, 20, hvEut, hvDur);
      addHVGun(Guns.SKS, 21, hvEut, hvDur);
      addHVGun(Guns.Dragonuv, 22, hvEut, hvDur);
      addHVGun(Guns.M21, 23, hvEut, hvDur);
      addHVGun(Guns.Saiga12, 1, hvEut, hvDur);
      addHVGun(Guns.Saiga410, 2, hvEut, hvDur);
      addHVGun(Guns.KSG12, 3, hvEut, hvDur);
      addHVGun(Guns.M1014, 4, hvEut, hvDur);
      addHVGun(Guns.QBS09, 5, hvEut, hvDur);
      addHVGun(Guns.VEPR12, 6, hvEut, hvDur);
      addHVGun(Guns.PP19, 7, hvEut, hvDur);
      addHVGun(Guns.UDP9, 8, hvEut, hvDur);
      addHVGun(Guns.MPX, 9, hvEut, hvDur);
      addHVGun(Guns.ACR, 10, hvEut, hvDur);
      addHVGun(Guns.M416, 11, hvEut, hvDur);
      addHVGun(Guns.POFUSA, 12, hvEut, hvDur);
      addHVGun(Guns.Ots14Groza, 13, hvEut, hvDur);
      addHVGun(Guns.TAR21, 14, hvEut, hvDur);
      addHVGun(Guns.UMP45, 15, hvEut, hvDur);
      addHVGun(Guns.ASVal, 16, hvEut, hvDur);
      addHVGun(Guns.VSSVintorez, 17, hvEut, hvDur);
      addHVGun(Guns.MP9, 18, hvEut, hvDur);
      addHVGun(Guns.Remington700, 19, hvEut, hvDur);
      addHVGun(Guns.M14, 20, hvEut, hvDur);
      addHVGun(Guns.Pistol10mm, 21, hvEut, hvDur);
      addHVGun(Guns.M110, 22, hvEut, hvDur);
      int evEut = 1920;
      int evDur = 600;
      addEVGun(Guns.ScarH, 1, evEut, evDur);
      addEVGun(Guns.HK417, 2, evEut, evDur);
      addEVGun(Guns.AN94, 3, evEut, evDur);
      addEVGun(Guns.AK12, 4, evEut, evDur);
      addEVGun(Guns.AK15, 5, evEut, evDur);
      addEVGun(Guns.MX, 6, evEut, evDur);
      addEVGun(Guns.M8A7, 7, evEut, evDur);
      addEVGun(Guns.L96, 8, evEut, evDur);
      addEVGun(Guns.SV98, 9, evEut, evDur);
      addEVGun(Guns.HecateII, 10, evEut, evDur);
      addEVGun(Guns.AS50, 11, evEut, evDur);
      addEVGun(Guns.M107, 12, evEut, evDur);
      addEVGun(Guns.M249, 13, evEut, evDur);
      addEVGun(Guns.Mk48MOD1, 14, evEut, evDur);
      addEVGun(Guns.RPK74M, 15, evEut, evDur);
      int ivEut = 7680;
      int ivDur = 1200;
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               new ItemStack(CommonProxy.GunBarrelTungstenSteel),
               new ItemStack(CommonProxy.GunReceiverTitanium),
               new ItemStack(CommonProxy.FiringMechanismAdvanced),
               new ItemStack(CommonProxy.GunStockCarbon),
               GTUtility.getIntegratedCircuit(1)
            }
         )
         .fluidInputs(new FluidStack[]{Materials.SolderingAlloy.getMolten(576L)})
         .itemOutputs(new ItemStack[]{new ItemStack(Guns.M41A)})
         .duration(ivDur)
         .eut(ivEut)
         .addTo(RecipeMaps.assemblerRecipes);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               new ItemStack(CommonProxy.GunBarrelTungstenSteel),
               new ItemStack(CommonProxy.GunReceiverTitanium),
               new ItemStack(CommonProxy.FiringMechanismAdvanced),
               new ItemStack(CommonProxy.GunStockCarbon),
               GTUtility.getIntegratedCircuit(2)
            }
         )
         .fluidInputs(new FluidStack[]{Materials.SolderingAlloy.getMolten(576L)})
         .itemOutputs(new ItemStack[]{new ItemStack(Guns.SMAW)})
         .duration(ivDur)
         .eut(ivEut)
         .addTo(RecipeMaps.assemblerRecipes);
   }

   private static void addLVGun(Object gunItem, int circuit, int eut, int dur) {
      if (gunItem != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.GunBarrelSteel),
                  new ItemStack(CommonProxy.GunReceiverSteel),
                  new ItemStack(CommonProxy.FiringMechanism),
                  new ItemStack(CommonProxy.GunStockWood),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack((Item)gunItem)})
            .duration(dur)
            .eut(eut)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addMVGun(Object gunItem, int circuit, int eut, int dur) {
      if (gunItem != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.GunBarrelStainless),
                  new ItemStack(CommonProxy.GunReceiverSteel),
                  new ItemStack(CommonProxy.FiringMechanism),
                  new ItemStack(CommonProxy.GunStockPlastic),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack((Item)gunItem)})
            .duration(dur)
            .eut(eut)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addHVGun(Object gunItem, int circuit, int eut, int dur) {
      if (gunItem != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.GunBarrelTitanium),
                  new ItemStack(CommonProxy.GunReceiverStainless),
                  new ItemStack(CommonProxy.FiringMechanismAdvanced),
                  new ItemStack(CommonProxy.GunStockPlastic),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .fluidInputs(new FluidStack[]{Materials.SolderingAlloy.getMolten(144L)})
            .itemOutputs(new ItemStack[]{new ItemStack((Item)gunItem)})
            .duration(dur)
            .eut(eut)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addEVGun(Object gunItem, int circuit, int eut, int dur) {
      if (gunItem != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.GunBarrelTungstenSteel),
                  new ItemStack(CommonProxy.GunReceiverTitanium),
                  new ItemStack(CommonProxy.FiringMechanismAdvanced),
                  new ItemStack(CommonProxy.GunStockCarbon),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .fluidInputs(new FluidStack[]{Materials.SolderingAlloy.getMolten(288L)})
            .itemOutputs(new ItemStack[]{new ItemStack((Item)gunItem)})
            .duration(dur)
            .eut(eut)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addAmmoRecipes() {
      addSmallAmmo(Bullets.Bullet9mm, 1, 16);
      addSmallAmmo(Bullets.Bullet45ACP, 2, 16);
      addSmallAmmo(Bullets.Bullet357, 3, 16);
      addSmallAmmo(Bullets.Bullet10mm, 4, 16);
      addSmallAmmo(Bullets.Bullet10x24, 5, 16);
      addSmallAmmo(Bullets.Bullet57x28, 6, 16);
      addSmallAmmo(Bullets.Bullet46x30, 7, 16);
      addSmallAmmo(Bullets.Bullet762x25, 8, 16);
      addSmallAmmo(Bullets.Bullet762x21, 9, 16);
      addSmallAmmo(Bullets.Bullet380200, 10, 16);
      addSmallAmmo(Bullets.Bullet455, 11, 16);
      addSmallAmmo(Bullets.Magnum44Ammo, 12, 16);
      addMediumAmmo(Bullets.Bullet762x39, 1, 8);
      addMediumAmmo(Bullets.Bullet556x45, 2, 8);
      addMediumAmmo(Bullets.Bullet556x39, 3, 8);
      addMediumAmmo(Bullets.Bullet762x54, 4, 8);
      addMediumAmmo(Bullets.Bullet762x51, 5, 8);
      addMediumAmmo(Bullets.Bullet545x39, 6, 8);
      addMediumAmmo(Bullets.Bullet65x39, 7, 8);
      addMediumAmmo(Bullets.Carbine30, 8, 8);
      addMediumAmmo(Bullets.Bullet300Blackout, 9, 8);
      addMediumAmmo(Bullets.Bullet9x39mm, 10, 8);
      addMediumAmmo(Bullets.BulletSpringfield3006, 11, 8);
      addMediumAmmo(Bullets.Bullet8mm, 12, 8);
      addMediumAmmo(Bullets.Bullet308, 13, 8);
      addMediumAmmo(Bullets.Bullet792x33, 14, 8);
      addMediumAmmo(Bullets.Bullet792x57, 15, 8);
      addMediumAmmo(Bullets.Bullet303British, 16, 8);
      addLargeAmmo(Bullets.BMG50, 1, 4);
      addLargeAmmo(Bullets.Bullet50, 2, 4);
      GTValues.RA
         .stdBuilder()
         .itemInputs(
            new ItemStack[]{
               new ItemStack(CommonProxy.BulletCasingSmall, 8),
               GTOreDictUnificator.get(OrePrefixes.round, Materials.Lead, 16L),
               GTUtility.getIntegratedCircuit(13)
            }
         )
         .itemOutputs(new ItemStack[]{new ItemStack(Bullets.ShotgunShell, 8)})
         .duration(100)
         .eut(30)
         .addTo(RecipeMaps.assemblerRecipes);
      if (Bullets.ShotgunShell410 != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.BulletCasingSmall, 8),
                  GTOreDictUnificator.get(OrePrefixes.round, Materials.Lead, 8L),
                  GTUtility.getIntegratedCircuit(14)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack(Bullets.ShotgunShell410, 8)})
            .duration(100)
            .eut(30)
            .addTo(RecipeMaps.assemblerRecipes);
      }

      if (Bullets.SMAWRocket != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.BulletCasingLarge, 2),
                  GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 4L),
                  GTOreDictUnificator.get(OrePrefixes.dust, Materials.Gunpowder, 4L),
                  GTUtility.getIntegratedCircuit(3)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack(Bullets.SMAWRocket, 1)})
            .duration(200)
            .eut(480)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addSmallAmmo(Object bullet, int circuit, int count) {
      if (bullet != null) {
         ItemStack output = new ItemStack((Item)bullet, count);
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.BulletCasingSmall, count),
                  GTOreDictUnificator.get(OrePrefixes.round, Materials.Lead, count / 2),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .itemOutputs(new ItemStack[]{output})
            .duration(100)
            .eut(30)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addMediumAmmo(Object bullet, int circuit, int count) {
      if (bullet != null) {
         ItemStack output = new ItemStack((Item)bullet, count);
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.BulletCasingMedium, count),
                  GTOreDictUnificator.get(OrePrefixes.round, Materials.Steel, count / 2),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .itemOutputs(new ItemStack[]{output})
            .duration(100)
            .eut(30)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addLargeAmmo(Object bullet, int circuit, int count) {
      if (bullet != null) {
         ItemStack output = new ItemStack((Item)bullet, count);
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.BulletCasingLarge, count),
                  GTOreDictUnificator.get(OrePrefixes.round, Materials.Steel, count),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .itemOutputs(new ItemStack[]{output})
            .duration(100)
            .eut(120)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addMagazineRecipes() {
      addMagazine(Magazines.Magazine762x39, Bullets.Bullet762x39, 30, 30);
      addMagazine(Magazines.PMAG762x39, Bullets.Bullet762x39, 30, 30);
      addMagazine(Magazines.Mag75rnd762x39, Bullets.Bullet762x39, 64, 120);
      addMagazine(Magazines.NATOMag1, Bullets.Bullet556x45, 30, 30);
      addMagazine(Magazines.NATOMag2, Bullets.Bullet556x45, 30, 30);
      addMagazine(Magazines.NATO20rnd, Bullets.Bullet762x51, 20, 30);
      addMagazine(Magazines.NATO40rnd, Bullets.Bullet556x45, 40, 120);
      addMagazine(Magazines.NATOFamasMag, Bullets.Bullet556x45, 30, 30);
      addMagazine(Magazines.NATOG36Mag, Bullets.Bullet556x45, 30, 30);
      addMagazine(Magazines.NATODrum100, Bullets.Bullet556x45, 64, 120);
      addMagazine(Magazines.AK12Mag, Bullets.Bullet545x39, 30, 30);
      addMagazine(Magazines.AK74MMag, Bullets.Bullet545x39, 30, 30);
      addMagazine(Magazines.AKS74UMag, Bullets.Bullet545x39, 30, 30);
      addMagazine(Magazines.AK15Mag, Bullets.Bullet762x39, 30, 30);
      addMagazine(Magazines.ScarHMag, Bullets.Bullet762x51, 20, 30);
      addMagazine(Magazines.HK417Mag, Bullets.Bullet762x51, 20, 30);
      addMagazine(Magazines.FALMag, Bullets.Bullet762x51, 20, 30);
      addMagazine(Magazines.M14DMRMag, Bullets.Bullet762x51, 20, 30);
      addMagazine(Magazines.M8A7Mag, Bullets.Bullet556x45, 30, 30);
      addMagazine(Magazines.MXMag, Bullets.Bullet65x39, 30, 30);
      addMagazine(Magazines.STG44Mag, Bullets.Bullet792x33, 30, 30);
      addMagazine(Magazines.SVT40mag, Bullets.Bullet762x54, 10, 30);
      addMagazine(Magazines.M2CarbineMag, Bullets.Carbine30, 30, 30);
      addMagazine(Magazines.M1CarbineMag, Bullets.Carbine30, 15, 30);
      addMagazine(Magazines.M16A1Mag, Bullets.Bullet556x45, 20, 30);
      addMagazine(Magazines.Glock21Mag, Bullets.Bullet9mm, 17, 30);
      addMagazine(Magazines.Glock32Mag, Bullets.Bullet9mm, 17, 30);
      addMagazine(Magazines.G18Mag, Bullets.Bullet9mm, 33, 30);
      addMagazine(Magazines.M9BerettaMag, Bullets.Bullet9mm, 15, 30);
      addMagazine(Magazines.DeagleMag, Bullets.Magnum44Ammo, 7, 30);
      addMagazine(Magazines.Deagle50Mag, Bullets.Bullet50, 7, 30);
      addMagazine(Magazines.LugerP08Mag, Bullets.Bullet9mm, 8, 30);
      addMagazine(Magazines.ColtM1911Mag, Bullets.Bullet45ACP, 7, 30);
      addMagazine(Magazines.Mag10mm, Bullets.Bullet10mm, 15, 30);
      addMagazine(Magazines.PythonClip, Bullets.Bullet357, 6, 30);
      addMagazine(Magazines.MP5KMag, Bullets.Bullet9mm, 30, 30);
      addMagazine(Magazines.HKMP7Mag, Bullets.Bullet46x30, 40, 30);
      addMagazine(Magazines.FNP90Mag, Bullets.Bullet57x28, 50, 30);
      addMagazine(Magazines.VectorMag, Bullets.Bullet9mm, 33, 30);
      addMagazine(Magazines.MPXmag, Bullets.Bullet9mm, 30, 30);
      addMagazine(Magazines.M1A1mag, Bullets.Bullet45ACP, 30, 30);
      addMagazine(Magazines.MP40Mag, Bullets.Bullet9mm, 32, 30);
      addMagazine(Magazines.MP18mag, Bullets.Bullet9mm, 32, 30);
      addMagazine(Magazines.M3A1Mag, Bullets.Bullet45ACP, 30, 30);
      addMagazine(Magazines.PPSH41DrumMag, Bullets.Bullet762x25, 64, 120);
      addMagazine(Magazines.PP19Mag, Bullets.Bullet9mm, 64, 120);
      addMagazine(Magazines.Tec9Mag, Bullets.Bullet9mm, 32, 30);
      addMagazine(Magazines.Type100Mag, Bullets.Bullet8mm, 30, 30);
      addMagazine(Magazines.Magazine9mm, Bullets.Bullet9mm, 15, 30);
      addMagazine(Magazines.DragunovMag, Bullets.Bullet762x54, 10, 30);
      addMagazine(Magazines.AS50Mag, Bullets.BMG50, 5, 30);
      addMagazine(Magazines.M107BMag, Bullets.BMG50, 10, 30);
      addMagazine(Magazines.M110Mag, Bullets.Bullet762x51, 20, 30);
      addMagazine(Magazines.L115Mag, Bullets.Bullet308, 5, 30);
      addMagazine(Magazines.SV98Mag, Bullets.Bullet762x54, 10, 30);
      addMagazine(Magazines.ASValMag, Bullets.Bullet9x39mm, 20, 30);
      addMagazine(Magazines.VSSVintorezMag, Bullets.Bullet9x39mm, 20, 30);
      addMagazine(Magazines.Mag308, Bullets.Bullet308, 5, 30);
      addMagazine(Magazines.LeeEnfieldMag, Bullets.Bullet303British, 10, 30);
      addMagazine(Magazines.HecateIIMag, Bullets.BMG50, 7, 30);
      addMagazine(Magazines.M240Mag, Bullets.Bullet762x51, 20, 30);
      addMagazine(Magazines.M249Mag, Bullets.Bullet556x45, 64, 120);
      addMagazine(Magazines.Mk48Mag, Bullets.Bullet762x51, 64, 120);
      addMagazine(Magazines.RPK74MMag, Bullets.Bullet545x39, 45, 30);
      addMagazine(Magazines.Saiga12mag, Bullets.ShotgunShell, 10, 30);
      addMagazine(Magazines.Saiga410Mag, Bullets.ShotgunShell410, 10, 30);
      addMagazine(Magazines.VEPR12Mag, Bullets.ShotgunShell, 12, 30);
      addMagazine(Magazines.M41AMag, Bullets.Bullet556x45, 30, 30);
   }

   private static void addMagazine(Object mag, Object ammo, int ammoCount, int eut) {
      if (mag != null && ammo != null) {
         int clampedAmmo = Math.min(ammoCount, 64);
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2L),
                  GTOreDictUnificator.get(OrePrefixes.spring, Materials.Steel, 1L),
                  new ItemStack((Item)ammo, clampedAmmo)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack((Item)mag)})
            .duration(200)
            .eut(eut)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addAttachmentRecipes() {
      addScope(Attachments.Reflex, 1, 120, 200);
      addScope(Attachments.Holo2, 2, 120, 200);
      addScope(Attachments.Holographic2, 3, 120, 200);
      addScope(Attachments.Kobra, 5, 120, 200);
      addScope(Attachments.ACOG, 6, 480, 300);
      addScope(Attachments.Specter, 7, 480, 300);
      addScope(Attachments.MicroT1, 8, 120, 200);
      addScope(Attachments.Leupold, 9, 1920, 400);
      addScope(Attachments.PSO1, 10, 480, 300);
      addScope(Attachments.OKP7, 12, 120, 200);
      addScope(Attachments.G36Scope, 4, 480, 300);
      addScope(Attachments.AUGScope, 11, 480, 300);
      addScope(Attachments.Scope, 13, 1920, 400);
      addScope(Attachments.HP, 14, 1920, 400);
      addScope(Attachments.Unertl, 15, 480, 300);
      addScope(Attachments.PUscope, 16, 120, 200);
      addScope(Attachments.PriscopicScope, 17, 480, 300);
      addGrip(Attachments.Grip2, 1);
      addGrip(Attachments.Grip, 2);
      addGrip(Attachments.StubbyGrip, 3);
      addGrip(Attachments.VGrip, 4);
      if (Attachments.Bipod != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.WeaponPartKit),
                  GTOreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 2L),
                  GTOreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 2L),
                  GTOreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Steel, 1L)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack(Attachments.Bipod)})
            .duration(200)
            .eut(120)
            .addTo(RecipeMaps.assemblerRecipes);
      }

      if (Attachments.Laser != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  GTOreDictUnificator.get(OrePrefixes.lens, Materials.Ruby, 1L),
                  GTOreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 1L),
                  GTUtility.getIntegratedCircuit(3)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack(Attachments.Laser)})
            .duration(200)
            .eut(480)
            .addTo(RecipeMaps.assemblerRecipes);
      }

      if (Attachments.Laser2 != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  GTOreDictUnificator.get(OrePrefixes.lens, Materials.Ruby, 1L),
                  GTOreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 1L),
                  GTUtility.getIntegratedCircuit(4)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack(Attachments.Laser2)})
            .duration(200)
            .eut(480)
            .addTo(RecipeMaps.assemblerRecipes);
      }

      addSuppressor(Attachments.Silencer9mm, Materials.Steel, 1, 120);
      addSuppressor(Attachments.Silencer762x39, Materials.Steel, 2, 120);
      addSuppressor(Attachments.Silencer556x45, Materials.StainlessSteel, 3, 480);
      addSuppressor(Attachments.Silencer762x51, Materials.StainlessSteel, 4, 480);
      addSuppressor(Attachments.Silencer556x39, Materials.StainlessSteel, 5, 480);
      addSuppressor(Attachments.Silencer45ACP, Materials.Steel, 6, 120);
      addSuppressor(Attachments.Silencer300AACBlackout, Materials.StainlessSteel, 7, 480);
      addSuppressor(Attachments.Silencer50BMG, Materials.Titanium, 8, 1920);
      addSuppressor(Attachments.Silencer762x54, Materials.StainlessSteel, 9, 480);
      addSuppressor(Attachments.Silencer12Gauge, Materials.StainlessSteel, 10, 480);
      addSuppressor(Attachments.Silencer65x39, Materials.StainlessSteel, 11, 480);
      addSuppressor(Attachments.Silencer57x38, Materials.Steel, 12, 120);
      addSuppressor(Attachments.Silencer357, Materials.Steel, 13, 120);
      addSuppressor(Attachments.SilencerMP7, Materials.Steel, 14, 120);
   }

   private static void addScope(Object scope, int circuit, int eut, int dur) {
      if (scope != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  new ItemStack(CommonProxy.PrecisionLens),
                  GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2L),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack((Item)scope)})
            .duration(dur)
            .eut(eut)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addGrip(Object grip, int circuit) {
      if (grip != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  GTOreDictUnificator.get(OrePrefixes.plate, Materials.Plastic, 2L),
                  GTOreDictUnificator.get(OrePrefixes.screw, Materials.Steel, 2L),
                  GTOreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 1L),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack((Item)grip)})
            .duration(200)
            .eut(30)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addSuppressor(Object suppressor, Materials material, int circuit, int eut) {
      if (suppressor != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  GTOreDictUnificator.get(OrePrefixes.pipeSmall, material, 1L),
                  GTOreDictUnificator.get(OrePrefixes.plate, material, 2L),
                  GTUtility.getIntegratedCircuit(circuit)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack((Item)suppressor)})
            .duration(200)
            .eut(eut)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }

   private static void addGrenadeRecipes() {
      if (Grenades.FuseGrenade != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2L),
                  GTOreDictUnificator.get(OrePrefixes.dust, Materials.Gunpowder, 1L),
                  GTOreDictUnificator.get(OrePrefixes.spring, Materials.Steel, 1L),
                  new ItemStack(CommonProxy.WeaponPartKit),
                  GTUtility.getIntegratedCircuit(1)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack(Grenades.FuseGrenade, 2)})
            .duration(200)
            .eut(120)
            .addTo(RecipeMaps.assemblerRecipes);
      }

      if (Grenades.ImpactGrenade != null) {
         GTValues.RA
            .stdBuilder()
            .itemInputs(
               new ItemStack[]{
                  GTOreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2L),
                  GTOreDictUnificator.get(OrePrefixes.dust, Materials.Gunpowder, 1L),
                  GTOreDictUnificator.get(OrePrefixes.spring, Materials.Steel, 1L),
                  new ItemStack(CommonProxy.WeaponPartKit),
                  GTUtility.getIntegratedCircuit(2)
               }
            )
            .itemOutputs(new ItemStack[]{new ItemStack(Grenades.ImpactGrenade, 2)})
            .duration(200)
            .eut(480)
            .addTo(RecipeMaps.assemblerRecipes);
      }
   }
}
