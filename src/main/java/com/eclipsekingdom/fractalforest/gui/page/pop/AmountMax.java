package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static com.eclipsekingdom.fractalforest.gui.page.Icons.BACKGROUND_ITEM;

public class AmountMax implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        int max = popData.getCurrentSpawner().getMax();

        menu.setItem(4, Icons.createIcon(Material.MELON_SEEDS, ChatColor.DARK_GRAY + "Max tree number"));
        menu.setItem(7, Icons.createBiome(popData.getCurrentBiome()));
        menu.setItem(8, Icons.createSpecies(popData.getCurrentSpawner().getSpecies()));
        menu.setItem(10, BACKGROUND_ITEM);
        menu.setItem(11, BACKGROUND_ITEM);

        if (max > popData.getCurrentSpawner().getMin()) {
            menu.setItem(12, Icons.VALUE_MANIPULATOR("-1", max + " trees"));
        } else {
            menu.setItem(12, BACKGROUND_ITEM);
        }

        menu.setItem(13, Icons.CURRENT_VALUE(Material.NETHER_STAR, "Max tree number", max + " trees"));

        if (max < 7) {
            menu.setItem(14, Icons.VALUE_MANIPULATOR("+1", max + " trees"));
        } else {
            menu.setItem(14, BACKGROUND_ITEM);
        }
        menu.setItem(15, BACKGROUND_ITEM);
        menu.setItem(16, BACKGROUND_ITEM);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot) {
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

        if (change > 0 && spawner.getMax() == 7) {
            change = 0;
        }

        if (change != 0) {
            MenuUtil.playClickSound(player);
            spawner.setMax(spawner.getMax() + change);
            if (spawner.getMax() < spawner.getMin()) {
                spawner.setMax(spawner.getMin());
            }
            if (spawner.getMax() > 7) {
                spawner.setMax(7);
            }
            populate(menu, sessionData);
        }
    }
}
