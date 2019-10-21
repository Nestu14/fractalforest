package com.eclipsekingdom.fractalforest.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Icons {

    public static ItemStack BORDER_ITEM = createIcon(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + "•");
    public static ItemStack BACKGROUND_ITEM = createIcon(Material.WHITE_STAINED_GLASS_PANE, ChatColor.WHITE + "•");
    public static ItemStack BACK_BUTTON = createIcon(Material.IRON_AXE, ChatColor.DARK_GRAY + "Back");
    public static ItemStack CLOSE = createIcon(Material.BARRIER, ChatColor.RED + "Close");


    public static ItemStack createIcon(Material material, String name) {
        ItemStack icon = new ItemStack(material, 1);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        icon.setItemMeta(meta);
        return icon;
    }

}
