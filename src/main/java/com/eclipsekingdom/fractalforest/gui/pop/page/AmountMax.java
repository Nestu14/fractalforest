package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static com.eclipsekingdom.fractalforest.gui.Icons.BACKGROUND_ITEM;

public class AmountMax extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {

        int max = popSessionData.getCurrentSpawner().getMax();

        menu.setItem(4, Icons.createIcon(Material.MELON_SEEDS, ChatColor.DARK_GRAY + "Max tree number"));
        menu.setItem(7, Icons.createBiome(popSessionData.getCurrentBiome()));
        menu.setItem(8, Icons.createSpecies(popSessionData.getCurrentSpawner().getSpecies()));
        menu.setItem(10, BACKGROUND_ITEM);
        menu.setItem(11, BACKGROUND_ITEM);

        if (max > popSessionData.getCurrentSpawner().getMin()) {
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
    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        TreeSpawner spawner = popSessionData.getCurrentSpawner();
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
            populate(menu, popSessionData);
        }
    }
}
