package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.MenuGlass;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum MenuType {
    POP(ChatColor.GOLD.toString() + ChatColor.BOLD + "Tree Populator", MenuGlass.ORANGE, ChatColor.GOLD),
    GENOME(ChatColor.GREEN.toString() + ChatColor.BOLD + "Tree Genome", MenuGlass.GREEN, ChatColor.DARK_GREEN),
    SAPLING(ChatColor.GREEN.toString() + ChatColor.BOLD + "Sapling", MenuGlass.GREEN, ChatColor.DARK_GREEN),
    GEN(ChatColor.GREEN.toString() + ChatColor.BOLD + "Tree Generator", MenuGlass.GREEN, ChatColor.DARK_GREEN),
    NOT_FOUND(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Page Not Found", MenuGlass.GRAY, ChatColor.GRAY),
    ;
    private String title;
    private MenuGlass glass;
    private ChatColor color;

    MenuType(String title, MenuGlass glass, ChatColor color) {
        this.title = title;
        this.glass = glass;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public MenuGlass getGlass() {
        return glass;
    }

    public ChatColor getColor() {
        return color;
    }
}
