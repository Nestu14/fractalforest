package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
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

public class AmountMin implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {

        PopData popData = sessionData.getPopData();

        int min = popData.getCurrentSpawner().getMin();

        menu.setItem(4, Icons.createIcon(Material.MELON_SEEDS, ChatColor.DARK_GRAY + "Min Tree Number"));
        menu.setItem(7, popData.getCurrentBiome().getItemStack());
        menu.setItem(8, Icons.createSpecies(popData.getCurrentSpawner().getSpecies()));
        menu.setItem(10, BACKGROUND_ITEM);
        menu.setItem(11, BACKGROUND_ITEM);

        if (min > 0) {
            menu.setItem(12, Icons.VALUE_MANIPULATOR("-1", min + " trees"));
        } else {
            menu.setItem(12, BACKGROUND_ITEM);
        }

        menu.setItem(13, Icons.CURRENT_VALUE(Material.NETHER_STAR, "Min Tree Number", min + " trees"));

        if (min < popData.getCurrentSpawner().getMax()) {
            menu.setItem(14, Icons.VALUE_MANIPULATOR("+1", min + " trees"));
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

        if (change < 0 && spawner.getMin() == 0) {
            change = 0;
        }

        if (change > 0 && spawner.getMin() == spawner.getMax()) {
            change = 0;
        }

        if (change != 0) {
            MenuUtil.playClickSound(player);
            spawner.setMin(spawner.getMin() + change);
            if (spawner.getMin() < 0) spawner.setMin(0);
            if (spawner.getMin() > spawner.getMax()) spawner.setMin(spawner.getMax());
            sessionData.registerEdit();
            populate(menu, sessionData);
        }
    }
}
