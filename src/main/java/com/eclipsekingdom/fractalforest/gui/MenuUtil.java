package com.eclipsekingdom.fractalforest.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuUtil {

    public static Inventory createGenericPop(Page page) {

        Inventory menu = createGeneric(page, MenuType.TREE_POPULATOR);

        if (page.hasPrevious()) {
            menu.setItem(0, Icons.BACK_BUTTON);
        }

        return menu;
    }

    private static Inventory createGeneric(Page page, MenuType type) {

        int rows = page.getRows();
        Inventory menu;
        menu = createInventory(rows, type, page.getTitle());

        //add border
        ItemStack themeItem = Icons.createIcon(type.getMaterial(), type.getColor() + "~");

        for (int i = 0; i < 9; i++) {
            ItemStack borderItem = i % 2 == 0 ? themeItem : Icons.BORDER_ITEM;
            menu.setItem(i, borderItem);
        }

        int offset = (rows - 1) * 9;
        for (int i = 0; i < 9; i++) {
            ItemStack borderItem = i % 2 == 0 ? themeItem : Icons.BORDER_ITEM;
            menu.setItem(i + offset, borderItem);
        }

        for (int i = 1; i < rows - 1; i++) {
            ItemStack borderItem = i % 2 == 0 ? themeItem : Icons.BORDER_ITEM;
            menu.setItem(9 * i, borderItem);
            menu.setItem(9 * i + 8, borderItem);
        }

        return menu;
    }

    private static Inventory createInventory(int rows, MenuType menuType, String subTitle) {
        return Bukkit.createInventory(null, rows * 9, menuType.getTitle() + " " + ChatColor.DARK_GRAY + ChatColor.ITALIC + "- " + subTitle);
    }

    public static void playClickSound(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 0.5f, 1.2f);
    }


}
