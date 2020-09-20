package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.PopData;
import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public class Spawner implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();

        TreeSpawner spawner = popData.getCurrentSpawner();

        menu.setItem(4, Icons.createIcon(Material.DISPENSER, ChatColor.DARK_GRAY + MENU_TREE_SPAWNER.toString()));
        menu.setItem(7, popData.getCurrentBiome().getItemStack());
        menu.setItem(8, Icons.createSpecies(popData.getCurrentSpawner().getSpecies()));

        ItemStack chanceItem = new ItemStack(Material.MELON_SEEDS);
        ItemMeta meta = chanceItem.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + MENU_CHANCE_PER_CHUNK.toString());
        NumberFormat formatter = new DecimalFormat("#0.00");
        meta.setLore(Collections.singletonList(ChatColor.GRAY + formatter.format(spawner.getChance() * 100) + "%"));
        chanceItem.setItemMeta(meta);
        menu.setItem(10, chanceItem);

        menu.setItem(11, Icons.BACKGROUND_ITEM);

        ItemStack overFlowItem = new ItemStack(Material.ENDER_PEARL);
        meta = overFlowItem.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + MENU_OVERFLOW_RADIUS.toString());
        meta.setLore(Collections.singletonList(ChatColor.GRAY.toString() + spawner.getOverflow() + " " + UNIT_BLOCKS));
        overFlowItem.setItemMeta(meta);
        menu.setItem(12, overFlowItem);

        menu.setItem(13, Icons.BACKGROUND_ITEM);

        ItemStack minItem = new ItemStack(Material.MELON_SEEDS);
        meta = minItem.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + MENU_MIN_TREE_NUMBER.toString());
        meta.setLore(Collections.singletonList(ChatColor.GRAY.toString() + spawner.getMin()));
        minItem.setItemMeta(meta);
        menu.setItem(14, minItem);

        menu.setItem(15, Icons.BACKGROUND_ITEM);

        ItemStack maxItem = new ItemStack(Material.MELON_SEEDS);
        meta = maxItem.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + MENU_MAX_TREE_NUMBER.toString());
        meta.setLore(Collections.singletonList(ChatColor.GRAY.toString() + spawner.getMax()));
        maxItem.setItemMeta(meta);
        menu.setItem(16, maxItem);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        if (slot == 10) {
            sessionData.transition(player, PageType.CHANCE);
        } else if (slot == 12) {
            sessionData.transition(player, PageType.OVERFLOW);
        } else if (slot == 14) {
            sessionData.transition(player, PageType.AMOUNT_MIN);
        } else if (slot == 16) {
            sessionData.transition(player, PageType.AMOUNT_MAX);
        }
    }
}
