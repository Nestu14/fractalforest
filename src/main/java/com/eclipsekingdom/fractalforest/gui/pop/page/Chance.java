package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.eclipsekingdom.fractalforest.gui.Icons.BACKGROUND_ITEM;

public class Chance extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {

        double chance = popSessionData.getCurrentSpawner().getChance() * 100;

        menu.setItem(4, Icons.createIcon(Material.MELON_SEEDS, ChatColor.DARK_GRAY + "Chance per Chunk"));
        menu.setItem(7, Icons.createBiome(popSessionData.getCurrentBiome()));
        menu.setItem(8, Icons.createSpecies(popSessionData.getCurrentSpawner().getSpecies()));
        menu.setItem(10, BACKGROUND_ITEM);
        NumberFormat formatter = new DecimalFormat("#0.00");

        if (chance > 0) {
            menu.setItem(11, Icons.VALUE_MANIPULATOR("-10", formatter.format(chance) + "%"));
            menu.setItem(12, Icons.VALUE_MANIPULATOR("-1", formatter.format(chance) + "%"));
        } else {
            menu.setItem(11, BACKGROUND_ITEM);
            menu.setItem(12, BACKGROUND_ITEM);
        }

        menu.setItem(13, Icons.CURRENT_VALUE(Material.NETHER_STAR, "Chance per Chunk", formatter.format(chance) + "%"));

        menu.setItem(14, Icons.VALUE_MANIPULATOR("+1", formatter.format(chance) + "%"));
        menu.setItem(15, Icons.VALUE_MANIPULATOR("+10", formatter.format(chance) + "%"));
        menu.setItem(16, BACKGROUND_ITEM);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        TreeSpawner spawner = popSessionData.getCurrentSpawner();
        int change = 0;
        switch (slot) {
            case 11:
                change = -10;
                break;
            case 12:
                change = -1;
                break;
            case 14:
                change = 1;
                break;
            case 15:
                change = 10;
                break;
            default:
                break;
        }

        if (change < 0 && spawner.getChance() == 0) {
            change = 0;
        }

        if (change != 0) {
            MenuUtil.playClickSound(player);
            spawner.setChance(spawner.getChance() + change/100d);
            if (spawner.getChance() < 0) {
                spawner.setChance(0);
            }
            populate(menu, popSessionData);
        }
    }
}