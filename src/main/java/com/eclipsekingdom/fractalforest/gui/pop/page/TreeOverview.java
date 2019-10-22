package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TreeOverview extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {
        TreePopulator pop = popSessionData.getPopulator();
        Biome biome = popSessionData.getCurrentBiome();
        List<TreeSpawner> spawners = pop.getBiomeToTreeSpawner().get(biome);

        menu.setItem(4, Icons.createIcon(Material.WRITABLE_BOOK, ChatColor.DARK_GRAY + "Edit Tree Spawners"));
        menu.setItem(8, Icons.createBiome(biome));

        int offset = popSessionData.getPageOffsetX();
        int treeSpawnerSize = spawners.size();

        for (int i = 0; i < 7; i++) {
            int index = i + 10;
            if (treeSpawnerSize > i + offset) {
                TreeSpawner treeSpawner = spawners.get(i + offset);
                menu.setItem(index, Icons.createTreeSpawner(treeSpawner));
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
        menu.setItem(31, Icons.createIcon(Material.STONE_BUTTON, "+" + popSessionData.getPageOffsetX()));
        menu.setItem(32, Icons.createIcon(Material.ARROW, "Scroll Right"));

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        if (slot == 30) {
            MenuUtil.playClickSound(player);
            popSessionData.scrollLeft();
            populate(menu, popSessionData);
        } else if (slot == 32) {
            MenuUtil.playClickSound(player);
            popSessionData.scrollRight();
            populate(menu, popSessionData);
        } else {
            ItemStack itemStack = menu.getItem(slot);
            TreePopulator pop = popSessionData.getPopulator();
            if (itemStack != null) {
                Material material = itemStack.getType();
                if (material == Material.LIME_STAINED_GLASS_PANE) {
                    MenuUtil.playClickSound(player);
                    PopPage select = PopPageType.TREE_SELECT.getPage();
                    popSessionData.setTransitioning(true);
                    player.openInventory(select.getInventory(popSessionData));
                    popSessionData.setTransitioning(false);
                    popSessionData.setCurrent(select);
                } else if (material == Material.RED_STAINED_GLASS_PANE) {
                    ItemStack treeStack = menu.getItem(slot - 9);
                    if (treeStack != null && treeStack.getType() != Material.AIR) {
                        ItemMeta meta = treeStack.getItemMeta();
                        String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
                        Species species = Species.from(name);
                        if(species != null){
                            Biome biome = popSessionData.getCurrentBiome();
                            List<TreeSpawner> spawners = pop.getBiomeToTreeSpawner().get(biome);
                            for(TreeSpawner spawner : spawners){
                                if(spawner.getSpecies() == species){
                                    spawners.remove(spawner);
                                    break;
                                }
                            }
                        }
                        MenuUtil.playClickSound(player);
                        populate(menu, popSessionData);
                    }
                }
            }
        }
    }


}
