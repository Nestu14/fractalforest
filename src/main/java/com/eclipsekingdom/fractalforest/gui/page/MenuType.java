package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.MenuGlass;
import org.bukkit.ChatColor;

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public enum MenuType {
    POP(ChatColor.GOLD.toString() + ChatColor.BOLD + LABEL_POP, MenuGlass.ORANGE, ChatColor.GOLD),
    GENOME(ChatColor.GREEN.toString() + ChatColor.BOLD + LABEL_GENOME, MenuGlass.GREEN, ChatColor.DARK_GREEN),
    SAPLING(ChatColor.GREEN.toString() + ChatColor.BOLD + LABEL_SAPLING, MenuGlass.GREEN, ChatColor.DARK_GREEN),
    GEN(ChatColor.GREEN.toString() + ChatColor.BOLD + LABEL_GENERATOR, MenuGlass.GREEN, ChatColor.DARK_GREEN),
    NOT_FOUND(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + LABEL_NO_PAGE, MenuGlass.GRAY, ChatColor.GRAY),
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
