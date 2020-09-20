package com.eclipsekingdom.fractalforest.gui;

import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.util.X.FGlass;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum MenuGlass {

    WHITE(XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial(), 0, ChatColor.WHITE),
    ORANGE(XMaterial.ORANGE_STAINED_GLASS_PANE.parseMaterial(), 1, ChatColor.GOLD),
    MAGENTA(XMaterial.MAGENTA_STAINED_GLASS_PANE.parseMaterial(), 2, ChatColor.LIGHT_PURPLE),
    LIGHT_BLUE(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE.parseMaterial(), 3, ChatColor.AQUA),
    YELLOW(XMaterial.YELLOW_STAINED_GLASS_PANE.parseMaterial(), 4, ChatColor.YELLOW),
    LIME(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial(), 5, ChatColor.GREEN),
    PINK(XMaterial.PINK_STAINED_GLASS_PANE.parseMaterial(), 6, ChatColor.LIGHT_PURPLE),
    GRAY(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial(), 7, ChatColor.DARK_GRAY),
    LIGHT_GRAY(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE.parseMaterial(), 8, ChatColor.GRAY),
    CYAN(XMaterial.CYAN_STAINED_GLASS_PANE.parseMaterial(), 9, ChatColor.DARK_AQUA),
    PURPLE(XMaterial.PURPLE_STAINED_GLASS_PANE.parseMaterial(), 10, ChatColor.DARK_PURPLE),
    BLUE(XMaterial.BLUE_STAINED_GLASS_PANE.parseMaterial(), 11, ChatColor.BLUE),
    BROWN(XMaterial.BROWN_STAINED_GLASS_PANE.parseMaterial(), 12, ChatColor.DARK_GRAY),
    GREEN(XMaterial.GREEN_STAINED_GLASS_PANE.parseMaterial(), 13, ChatColor.DARK_GREEN),
    RED(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial(), 14, ChatColor.RED),
    BLACK(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial(), 15, ChatColor.DARK_GRAY),

    ;

    private ItemStack swiggleItem;
    private ItemStack dotItem;

    private Material material;
    private byte data;

    MenuGlass(Material material, int data, ChatColor color) {
        this.material = material;
        this.data = (byte) data;
        this.swiggleItem = buildItem(data, color, "~");
        this.dotItem = buildItem(data, color, "•");
    }

    public ItemStack getSwiggleItem() {
        return swiggleItem;
    }

    public ItemStack getDotItem() {
        return dotItem;
    }

    public static ItemStack getCustom(int data, ChatColor color, String name) {
        return buildItem(data, color, name);
    }

    private static ItemStack buildItem(int data, ChatColor color, String name) {
        ItemStack itemStack;
        if (Version.current.value >= 113) {
            itemStack = FGlass.dataToGlassColor.containsKey(data) ? new ItemStack(FGlass.dataToGlassColor.get(data)) :
                    new ItemStack(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial());
        } else {
            itemStack = FGlass.dataToGlassColor.containsKey(data) ? new ItemStack(XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial(), 1, (short) data) :
                    new ItemStack(XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial(), 1, (short) 15);
        }
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(color + name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public byte getData() {
        return data;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getItemStack() {
        if (Version.current.value >= 113) {
            return new ItemStack(material);
        } else {
            return new ItemStack(material, 1, data);
        }
    }

}
