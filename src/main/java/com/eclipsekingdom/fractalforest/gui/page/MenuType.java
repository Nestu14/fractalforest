package com.eclipsekingdom.fractalforest.gui.page;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum MenuType {
    TREE_POPULATOR(ChatColor.GOLD.toString() + ChatColor.BOLD + "Tree Populator", Material.ORANGE_STAINED_GLASS_PANE, ChatColor.GOLD),
    TREE_GENOME(ChatColor.GREEN.toString() + ChatColor.BOLD + "Tree Genome", Material.GREEN_STAINED_GLASS_PANE, ChatColor.DARK_GREEN),
    SAPLING(ChatColor.GREEN.toString() + ChatColor.BOLD + "Sapling", Material.GREEN_STAINED_GLASS_PANE, ChatColor.DARK_GREEN),
    NOT_FOUND(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Page Not Found", Material.GRAY_STAINED_GLASS_PANE, ChatColor.GRAY),
    ;
    private String title;
    private Material material;
    private ChatColor color;

    MenuType(String title, Material material, ChatColor color) {
        this.title = title;
        this.material = material;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public Material getMaterial() {
        return material;
    }

    public ChatColor getColor() {
        return color;
    }
}
