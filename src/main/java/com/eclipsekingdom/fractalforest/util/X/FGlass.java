package com.eclipsekingdom.fractalforest.util.X;

import com.eclipsekingdom.fractalforest.gui.MenuGlass;
import com.eclipsekingdom.fractalforest.sys.Version;
import com.google.common.collect.ImmutableMap;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FGlass {

    public static ImmutableMap<Integer, Material> dataToGlassColor = new ImmutableMap.Builder<Integer, Material>()
            .put(0, XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial())
            .put(1, XMaterial.ORANGE_STAINED_GLASS_PANE.parseMaterial())
            .put(2, XMaterial.MAGENTA_STAINED_GLASS_PANE.parseMaterial())
            .put(3, XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE.parseMaterial())
            .put(4, XMaterial.YELLOW_STAINED_GLASS_PANE.parseMaterial())
            .put(5, XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial())
            .put(6, XMaterial.PINK_STAINED_GLASS_PANE.parseMaterial())
            .put(7, XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial())
            .put(8, XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE.parseMaterial())
            .put(9, XMaterial.CYAN_STAINED_GLASS_PANE.parseMaterial())
            .put(10, XMaterial.PURPLE_STAINED_GLASS_PANE.parseMaterial())
            .put(11, XMaterial.BLUE_STAINED_GLASS_PANE.parseMaterial())
            .put(12, XMaterial.BROWN_STAINED_GLASS_PANE.parseMaterial())
            .put(13, XMaterial.GREEN_STAINED_GLASS_PANE.parseMaterial())
            .put(14, XMaterial.RED_STAINED_GLASS_PANE.parseMaterial())
            .put(15, XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
            .build();

    public static boolean equals(ItemStack itemStack, MenuGlass menuGlass) {
        if(Version.current.value >= 113){
            return itemStack.getType().equals(menuGlass.getMaterial());
        }else{
            return itemStack.getType().equals(menuGlass.getMaterial()) && itemStack.getData().getData() == menuGlass.getData();
        }
    }


}




