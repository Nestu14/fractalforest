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

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.eclipsekingdom.fractalforest.gui.page.Icons.BACKGROUND_ITEM;
import static com.eclipsekingdom.fractalforest.sys.language.Message.MENU_CHANCE_PER_CHUNK;

public class Chance implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {

        PopData popData = sessionData.getPopData();

        double chance = popData.getCurrentSpawner().getChance() * 100;

        menu.setItem(4, Icons.createIcon(Material.MELON_SEEDS, ChatColor.DARK_GRAY + MENU_CHANCE_PER_CHUNK.toString()));
        menu.setItem(7, popData.getCurrentBiome().getItemStack());
        menu.setItem(8, Icons.createSpecies(popData.getCurrentSpawner().getSpecies()));
        NumberFormat formatter = new DecimalFormat("#0.00");

        if (chance > 0) {
            menu.setItem(10, Icons.VALUE_MANIPULATOR("-10", formatter.format(chance) + "%"));
            menu.setItem(11, Icons.VALUE_MANIPULATOR("-1", formatter.format(chance) + "%"));
            menu.setItem(12, Icons.VALUE_MANIPULATOR("-0.1", formatter.format(chance) + "%"));
        } else {
            menu.setItem(10, BACKGROUND_ITEM);
            menu.setItem(11, BACKGROUND_ITEM);
            menu.setItem(12, BACKGROUND_ITEM);
        }

        menu.setItem(13, Icons.CURRENT_VALUE(Material.NETHER_STAR, MENU_CHANCE_PER_CHUNK.toString(), formatter.format(chance) + "%"));

        if (chance < 100) {
            menu.setItem(14, Icons.VALUE_MANIPULATOR("+0.1", formatter.format(chance) + "%"));
            menu.setItem(15, Icons.VALUE_MANIPULATOR("+1", formatter.format(chance) + "%"));
            menu.setItem(16, Icons.VALUE_MANIPULATOR("+10", formatter.format(chance) + "%"));
        } else {
            menu.setItem(14, BACKGROUND_ITEM);
            menu.setItem(15, BACKGROUND_ITEM);
            menu.setItem(16, BACKGROUND_ITEM);
        }

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        TreeSpawner spawner = popData.getCurrentSpawner();
        double change = 0;
        switch (slot) {
            case 10:
                change = -10;
                break;
            case 11:
                change = -1;
                break;
            case 12:
                change = -0.1;
                break;
            case 14:
                change = 0.1;
                break;
            case 15:
                change = 1;
                break;
            case 16:
                change = 10;
                break;
            default:
                break;
        }

        if (change < 0 && spawner.getChance() == 0) change = 0;

        if (change > 0 && spawner.getChance() == 1) change = 0;

        if (change != 0) {
            MenuUtil.playClickSound(player);
            spawner.setChance(spawner.getChance() + change / 100d);
            if (spawner.getChance() < 0) spawner.setChance(0);
            if (spawner.getChance() > 1) spawner.setChance(1);
            sessionData.registerEdit();
            populate(menu, sessionData);
        }
    }

}
