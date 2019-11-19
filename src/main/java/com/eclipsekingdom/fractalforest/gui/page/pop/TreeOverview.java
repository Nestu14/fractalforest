package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.gen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.gen.pop.TreeSpawner;
import com.eclipsekingdom.fractalforest.gen.pop.util.TreeBiome;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TreeOverview implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        TreeBiome biome = popData.getCurrentBiome();
        List<TreeSpawner> spawners = pop.getBiomeToTreeSpawner().get(biome);

        menu.setItem(4, Icons.createIcon(Material.WRITABLE_BOOK, ChatColor.DARK_GRAY + "Edit Tree Spawners"));
        menu.setItem(8, Icons.createBiome(biome));

        int offset = sessionData.getPageOffsetX();
        int treeSpawnerSize = spawners.size();

        for (int i = 0; i < 7; i++) {
            int index = i + 10;
            if (treeSpawnerSize > i + offset) {
                TreeSpawner treeSpawner = spawners.get(i + offset);
                menu.setItem(index, Icons.createTreeSpawnerType(treeSpawner));
                if (treeSpawnerSize > 1) {
                    menu.setItem(index + 9, Icons.createIcon(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "X"));
                } else {
                    menu.setItem(index + 9, Icons.BACKGROUND_ITEM);
                }
            } else {
                if (treeSpawnerSize > i - 1 + offset) {
                    menu.setItem(index, Icons.createIcon(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "+"));
                } else {
                    menu.setItem(index, Icons.BACKGROUND_ITEM);
                }
                menu.setItem(index + 9, Icons.BACKGROUND_ITEM);
            }
        }

        menu.setItem(30, Icons.createIcon(Material.ARROW, "Scroll Left"));
        menu.setItem(31, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetX()));
        menu.setItem(32, Icons.createIcon(Material.ARROW, "Scroll Right"));

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        if (slot == 30) {
            sessionData.scrollLeft(player, this, menu);
        } else if (slot == 32) {
            sessionData.scrollRight(player, this, menu);
        } else {
            ItemStack itemStack = menu.getItem(slot);
            TreePopulator pop = popData.getPopulator();
            if (itemStack != null) {
                Material material = itemStack.getType();
                if (material == Material.LIME_STAINED_GLASS_PANE) {
                    sessionData.transition(player, PageType.TREE_SELECT);
                } else if (material == Material.RED_STAINED_GLASS_PANE) {
                    ItemStack treeStack = menu.getItem(slot - 9);
                    if (treeStack != null && treeStack.getType() != Material.AIR) {
                        ItemMeta meta = treeStack.getItemMeta();
                        String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
                        Species species = Species.from(name);
                        if (species != null) {
                            TreeBiome biome = popData.getCurrentBiome();
                            List<TreeSpawner> spawners = pop.getBiomeToTreeSpawner().get(biome);
                            for (TreeSpawner spawner : spawners) {
                                if (spawner.getSpecies() == species) {
                                    spawners.remove(spawner);
                                    break;
                                }
                            }
                        }
                        MenuUtil.playClickSound(player);
                        populate(menu, sessionData);
                    }
                }
            }
        }
    }


}
