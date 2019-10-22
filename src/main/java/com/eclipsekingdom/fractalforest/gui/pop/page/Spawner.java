package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;

public class Spawner extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {

        TreeSpawner spawner = popSessionData.getCurrentSpawner();

        menu.setItem(4, Icons.createIcon(Material.DISPENSER, ChatColor.DARK_GRAY + "Tree Spawner"));
        menu.setItem(7, Icons.createBiome(popSessionData.getCurrentBiome()));
        menu.setItem(8, Icons.createSpecies(popSessionData.getCurrentSpawner().getSpecies()));

        menu.setItem(10, Icons.BACKGROUND_ITEM);
        ItemStack chanceItem = new ItemStack(Material.MELON_SEEDS);
        ItemMeta meta = chanceItem.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Chance per Chunk");
        NumberFormat formatter = new DecimalFormat("#0.00");
        meta.setLore(Collections.singletonList(ChatColor.GRAY + formatter.format(spawner.getChance()*100) + "%"));
        chanceItem.setItemMeta(meta);

        menu.setItem(11, chanceItem);
        menu.setItem(12, Icons.BACKGROUND_ITEM);

        ItemStack minItem = new ItemStack(Material.MELON_SEEDS);
        meta = minItem.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Min tree number");
        meta.setLore(Collections.singletonList(ChatColor.GRAY.toString() + spawner.getMin()));
        minItem.setItemMeta(meta);

        menu.setItem(13, minItem);
        menu.setItem(14, Icons.BACKGROUND_ITEM);

        ItemStack maxItem = new ItemStack(Material.MELON_SEEDS);
        meta = maxItem.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Max tree number");
        meta.setLore(Collections.singletonList(ChatColor.GRAY.toString() + spawner.getMax()));
        maxItem.setItemMeta(meta);

        menu.setItem(15, maxItem);
        menu.setItem(16, Icons.BACKGROUND_ITEM);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        if (slot == 11) {
            PopPage chance = PopPageType.CHANCE.getPage();
            popSessionData.setCurrent(chance);
            popSessionData.setTransitioning(true);
            player.openInventory(chance.getInventory(popSessionData));
            popSessionData.setTransitioning(false);
        } else if (slot == 13) {
            PopPage overview = PopPageType.AMOUNT_MIN.getPage();
            popSessionData.setCurrent(overview);
            popSessionData.setTransitioning(true);
            player.openInventory(overview.getInventory(popSessionData));
            popSessionData.setTransitioning(false);
        } else if (slot == 15) {
            PopPage overview = PopPageType.AMOUNT_MAX.getPage();
            popSessionData.setCurrent(overview);
            popSessionData.setTransitioning(true);
            player.openInventory(overview.getInventory(popSessionData));
            popSessionData.setTransitioning(false);
        }
    }
}
