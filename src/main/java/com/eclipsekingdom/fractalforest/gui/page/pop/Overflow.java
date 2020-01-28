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
import static com.eclipsekingdom.fractalforest.sys.language.Message.MENU_OVERFLOW_RADIUS;
import static com.eclipsekingdom.fractalforest.sys.language.Message.UNIT_BLOCKS;

public class Overflow implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {

        PopData popData = sessionData.getPopData();

        int overflow = popData.getCurrentSpawner().getOverflow();

        menu.setItem(4, Icons.createIcon(Material.ENDER_PEARL, ChatColor.DARK_GRAY + MENU_OVERFLOW_RADIUS.toString()));
        menu.setItem(7, popData.getCurrentBiome().getItemStack());
        menu.setItem(8, Icons.createSpecies(popData.getCurrentSpawner().getSpecies()));
        menu.setItem(10, BACKGROUND_ITEM);

        if (overflow > 0) {
            menu.setItem(11, Icons.VALUE_MANIPULATOR("-10", overflow + " " + UNIT_BLOCKS));
            menu.setItem(12, Icons.VALUE_MANIPULATOR("-1", overflow + " " + UNIT_BLOCKS));
        } else {
            menu.setItem(11, BACKGROUND_ITEM);
            menu.setItem(12, BACKGROUND_ITEM);
        }

        menu.setItem(13, Icons.CURRENT_VALUE(Material.NETHER_STAR, MENU_OVERFLOW_RADIUS.toString(), overflow + " " + UNIT_BLOCKS));

        if (overflow < 64) {
            menu.setItem(14, Icons.VALUE_MANIPULATOR("+1", overflow + " " + UNIT_BLOCKS));
            menu.setItem(15, Icons.VALUE_MANIPULATOR("+10", overflow + " " + UNIT_BLOCKS));
        } else {
            menu.setItem(14, BACKGROUND_ITEM);
            menu.setItem(15, BACKGROUND_ITEM);
        }

        menu.setItem(16, BACKGROUND_ITEM);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        TreeSpawner spawner = popData.getCurrentSpawner();
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

        if (change < 0 && spawner.getOverflow() == 0) change = 0;

        if (change > 0 && spawner.getOverflow() == 64) change = 0;

        if (change != 0) {
            MenuUtil.playClickSound(player);
            spawner.setOverflow(spawner.getOverflow() + change);
            if (spawner.getOverflow() < 0) spawner.setOverflow(0);
            if (spawner.getOverflow() > 64) spawner.setOverflow(64);
            sessionData.registerEdit();
            populate(menu, sessionData);
        }
    }

}