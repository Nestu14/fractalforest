package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.PopData;
import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class PopHome implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();

        LinkedHashMap<TreeBiome, List<TreeSpawner>> biomeToTreeSpawners = pop.getBiomeToTreeSpawner();
        List<TreeBiome> biomes = new ArrayList<>();
        for (TreeBiome biome : biomeToTreeSpawners.keySet()) {
            if(biome != TreeBiome.NONE){
                biomes.add(biome);
            }
        }
        menu.setItem(4, Icons.createIcon(Material.NAME_TAG, ChatColor.GRAY + pop.getName()));

        int offset = sessionData.getPageOffsetX();
        int biomesSize = biomeToTreeSpawners.size();

        for (int i = 0; i < 7; i++) {
            int index = i + 10;
            if (biomesSize > i + offset) {
                TreeBiome biome = biomes.get(i + offset);
                createColumn(menu, index, biome, biomeToTreeSpawners.get(biome), sessionData.getPageOffsetY());
            } else {
                if (biomesSize > i - 1 + offset) {
                    menu.setItem(index, Icons.createIcon(Material.WRITABLE_BOOK, ChatColor.GRAY + "Edit"));
                } else {
                    menu.setItem(index, Icons.BACKGROUND_ITEM);
                }
                for (int j = 1; j < 4; j++) {
                    menu.setItem(index + (9 * j), Icons.BACKGROUND_ITEM);
                }
            }
        }

        menu.setItem(26, Icons.createIcon(Material.TRIPWIRE_HOOK, "Scroll Up"));
        menu.setItem(35, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetY()));
        menu.setItem(44, Icons.createIcon(Material.HOPPER, "Scroll Down"));

        menu.setItem(48, Icons.createIcon(Material.ARROW, "Scroll Left"));
        menu.setItem(49, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetX()));
        menu.setItem(50, Icons.createIcon(Material.ARROW, "Scroll Right"));


        return menu;
    }

    private void createColumn(Inventory menu, int column, TreeBiome biome, List<TreeSpawner> treeSpawners, int offsetY) {
        menu.setItem(column, Icons.createBiome(biome));
        int offset = offsetY;
        int spawnersSize = treeSpawners.size();
        for (int i = 0; i < 3; i++) {
            int index = column + 9 + (9 * i);
            if (spawnersSize > i + offset) {
                TreeSpawner treeSpawner = treeSpawners.get(i + offset);
                menu.setItem(index, Icons.createTreeSpawner(treeSpawner));
            } else {
                if (spawnersSize > i - 1 + offset) {
                    menu.setItem(index, Icons.createIcon(Material.WRITABLE_BOOK, ChatColor.GRAY + "Edit"));
                } else {
                    menu.setItem(index, Icons.BACKGROUND_ITEM);
                }
            }
        }
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        if (slot == 26) {
            sessionData.scrollUp(player, this, menu);
        } else if (slot == 44) {
            sessionData.scrollDown(player, this, menu);
        } else if (slot == 48) {
            sessionData.scrollLeft(player, this, menu);
        } else if (slot == 50) {
            sessionData.scrollRight(player, this, menu);
        } else {
            ItemStack itemStack = menu.getItem(slot);
            if (itemStack != null && itemStack.getType() != Icons.BACKGROUND_ITEM.getType()) {
                if (itemStack.getType() == Material.WRITABLE_BOOK) {
                    if (slot / 9 == 1) {
                        sessionData.transition(player, PageType.BIOME_OVERVIEW);
                    } else {
                        int top = slot % 9 + 9;
                        ItemStack biomeItem = menu.getItem(top);
                        TreeBiome biome = TreeBiome.valueOf(biomeItem.getItemMeta().getDisplayName());
                        popData.setCurrentBiome(biome);
                        sessionData.transition(player, PageType.TREE_OVERVIEW);
                    }
                } else if (!isCorner(slot)) {
                    int top = slot % 9 + 9;
                    ItemStack biomeItem = menu.getItem(top);
                    if (biomeItem != null) {
                        TreePopulator pop = popData.getPopulator();
                        try {
                            TreeBiome biome = TreeBiome.valueOf(biomeItem.getItemMeta().getDisplayName());
                            List<TreeSpawner> treeSpawners = pop.getBiomeToTreeSpawner().get(biome);
                            ItemStack spawnStack = menu.getItem(slot);
                            if (spawnStack != null && spawnStack.getType() != Icons.BACKGROUND_ITEM.getType() && spawnStack.getType() != Material.WRITABLE_BOOK) {
                                ItemMeta meta = spawnStack.getItemMeta();
                                String name = meta.getDisplayName();
                                for (TreeSpawner treeSpawner : treeSpawners) {
                                    if (treeSpawner.getSpecies().toString().equals(name)) {
                                        popData.setCurrentSpawner(treeSpawner);
                                        popData.setCurrentBiome(biome);
                                        sessionData.transition(player, PageType.SPAWNER);
                                        break;
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }


    private boolean isCorner(int slot) {
        return slot % 9 == 0 || (slot + 1) % 9 == 0;
    }

}
