package com.gtnewhorizon.newgunrizons.client;

import java.util.ArrayList;
import java.util.List;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class ADSConfigGui extends GuiConfig {

    public ADSConfigGui(GuiScreen parent) {
        super(
            parent,
            getConfigElements(),
            NewGunrizonsMod.MODID,
            false,
            false,
            "NewGunrizons Config"
        );
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> elements = new ArrayList<>();
        elements.addAll(new ConfigElement(
            ADSConfig.config.getCategory("client")).getChildElements());
        return elements;
    }
}
