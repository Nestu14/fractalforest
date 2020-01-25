package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreeSpawner;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TreeSelect implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        menu.setItem(4, Icons.createIcon(Material.OAK_SAPLING, "Tree Selection"));
        TreeBiome biome = popData.getCurrentBiome();
        menu.setItem(8, biome.getItemStack());

        int index = 10;
        for (Species species : Species.values()) {
            if (index < 44) {
                menu.setItem(index, Icons.createTreeSpawnerType(TreeSpawner.defaultTreeSpawner(species)));
            }
            if ((index + 2) % 9 == 0) {
                index += 3;
            } else {
                index++;
            }
        }
        while (index < 44) {
            menu.setItem(index, Icons.BACKGROUND_ITEM);
            if ((index + 2) % 9 == 0) {
                index += 3;
            } else {
                index++;
            }
        }

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        ItemStack itemStack = menu.getItem(slot);
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            ItemMeta meta = itemStack.getItemMeta();
            String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
            try {
                Species species = Species.valueOf(name);
                if (species != null) {
                    TreeSpawner treeSpawner = TreeSpawner.defaultTreeSpawner(species);
                    TreeBiome biome = popData.getCurrentBiome();
                    popData.getPopulator().getBiomeToTreeSpawner().get(biome).add(treeSpawner);
                    sessionData.transition(player, PageType.TREE_OVERVIEW);
                    sessionData.registerEdit();
                }
            } catch (Exception e) {
            }
        }
    }
}
