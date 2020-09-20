package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.PopData;
import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import static com.eclipsekingdom.fractalforest.gui.page.Icons.BACKGROUND_ITEM;
import static com.eclipsekingdom.fractalforest.sys.language.Message.MENU_MAX_TREE_NUMBER;
import static com.eclipsekingdom.fractalforest.sys.language.Message.UNIT_TREES;

public class AmountMax implements PageContents {

    private static final int MAX = 9;

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        int max = popData.getCurrentSpawner().getMax();

        menu.setItem(4, Icons.createIcon(Material.MELON_SEEDS, ChatColor.DARK_GRAY + MENU_MAX_TREE_NUMBER.toString()));
        menu.setItem(7, popData.getCurrentBiome().getItemStack());
        menu.setItem(8, Icons.createSpecies(popData.getCurrentSpawner().getSpecies()));
        menu.setItem(10, BACKGROUND_ITEM);
        menu.setItem(11, BACKGROUND_ITEM);

        if (max > popData.getCurrentSpawner().getMin()) {
            menu.setItem(12, Icons.VALUE_MANIPULATOR("-1", max + " " + UNIT_TREES));
        } else {
            menu.setItem(12, BACKGROUND_ITEM);
        }

        menu.setItem(13, Icons.CURRENT_VALUE(Material.NETHER_STAR, MENU_MAX_TREE_NUMBER.toString(), max + " " + UNIT_TREES));

        if (max < MAX) {
            menu.setItem(14, Icons.VALUE_MANIPULATOR("+1", max + " " + UNIT_TREES));
        } else {
            menu.setItem(14, BACKGROUND_ITEM);
        }
        menu.setItem(15, BACKGROUND_ITEM);
        menu.setItem(16, BACKGROUND_ITEM);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        TreeSpawner spawner = popData.getCurrentSpawner();
        int change = 0;
        switch (slot) {
            case 12:
                change = -1;
                break;
            case 14:
                change = 1;
                break;
            default:
                break;
        }

        if (change < 0 && spawner.getMax() == spawner.getMin()) {
            change = 0;
        }

        if (change > 0 && spawner.getMax() == MAX) {
            change = 0;
        }

        if (change != 0) {
            MenuUtil.playClickSound(player);
            spawner.setMax(spawner.getMax() + change);
            if (spawner.getMax() < spawner.getMin()) spawner.setMax(spawner.getMin());
            if (spawner.getMax() > MAX) spawner.setMax(MAX);
            sessionData.registerEdit();
            populate(menu, sessionData);
        }
    }
}
