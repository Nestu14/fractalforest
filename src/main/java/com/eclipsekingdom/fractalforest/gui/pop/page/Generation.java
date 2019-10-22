package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Generation extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {
        TreePopulator pop = popSessionData.getPopulator();

        LinkedHashMap<Biome, List<TreeSpawner>> biomeToTreeSpawners = pop.getBiomeToTreeSpawner();
        List<Biome> biomes = new ArrayList<>();
        for (Biome biome : biomeToTreeSpawners.keySet()) {
            biomes.add(biome);
        }
        menu.setItem(4, Icons.createIcon(Material.WHEAT_SEEDS, ChatColor.GREEN + "Generator"));

        int offset = popSessionData.getPageOffsetX();
        int biomesSize = biomeToTreeSpawners.size();

        for (int i = 0; i < 7; i++) {
            int index = i + 10;
            if (biomesSize > i + offset) {
                createColumn(menu, index, biomes.get(i + offset), biomeToTreeSpawners, popSessionData.getPageOffsetY());
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
        menu.setItem(35, Icons.createIcon(Material.STONE_BUTTON, "+" + popSessionData.getPageOffsetY()));
        menu.setItem(44, Icons.createIcon(Material.HOPPER, "Scroll Down"));

        menu.setItem(48, Icons.createIcon(Material.ARROW, "Scroll Left"));
        menu.setItem(49, Icons.createIcon(Material.STONE_BUTTON, "+" + popSessionData.getPageOffsetX()));
        menu.setItem(50, Icons.createIcon(Material.ARROW, "Scroll Right"));


        return menu;
    }

    private void createColumn(Inventory menu, int column, Biome biome, Map<Biome, List<TreeSpawner>> biomeToTreeSpawners, int offsetY) {
        menu.setItem(column, Icons.createBiome(biome));
        List<TreeSpawner> treeSpawners = biomeToTreeSpawners.get(biome);
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
    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        if(slot == 26){
            MenuUtil.playClickSound(player);
            popSessionData.scrollUp();
            populate(menu, popSessionData);
        }else if(slot == 44){
            MenuUtil.playClickSound(player);
            popSessionData.scrollDown();
            populate(menu, popSessionData);
        }else if(slot == 48){
            MenuUtil.playClickSound(player);
            popSessionData.scrollLeft();
            populate(menu, popSessionData);
        }else if(slot == 50){
            MenuUtil.playClickSound(player);
            popSessionData.scrollRight();
            populate(menu, popSessionData);
        }else{
            ItemStack itemStack = menu.getItem(slot);
            if(itemStack != null && itemStack.getType() != Icons.BACKGROUND_ITEM.getType()){
                if (itemStack.getType() == Material.WRITABLE_BOOK) {
                    MenuUtil.playClickSound(player);
                    if(slot/9 == 1){
                        PopPage biomes = PopPageType.BIOME_OVERVIEW.getPage();
                        popSessionData.setCurrent(biomes);
                        popSessionData.setTransitioning(true);
                        player.openInventory(biomes.getInventory(popSessionData));
                        popSessionData.setTransitioning(false);
                    }else{
                        int top = slot%9 + 9;
                        ItemStack biomeItem = menu.getItem(top);
                        Biome biome = Biome.valueOf(biomeItem.getItemMeta().getDisplayName());
                        popSessionData.setCurrentBiome(biome);
                        PopPage trees = PopPageType.TREE_OVERVIEW.getPage();
                        popSessionData.setCurrent(trees);
                        popSessionData.setTransitioning(true);
                        player.openInventory(trees.getInventory(popSessionData));
                        popSessionData.setTransitioning(false);
                    }
                }else{
                    int top = slot%9 + 9;
                    ItemStack biomeItem = menu.getItem(top);
                    if(biomeItem != null){
                        TreePopulator pop = popSessionData.getPopulator();
                        try{
                            Biome biome = Biome.valueOf(biomeItem.getItemMeta().getDisplayName());
                            List<TreeSpawner> treeSpawners = pop.getBiomeToTreeSpawner().get(biome);
                            ItemStack spawnStack = menu.getItem(slot);
                            if(spawnStack != null && spawnStack.getType() != Icons.BACKGROUND_ITEM.getType()){
                                ItemMeta meta = spawnStack.getItemMeta();
                                String name = meta.getDisplayName();
                                for(TreeSpawner treeSpawner: treeSpawners){
                                    if(treeSpawner.getSpecies().toString().equals(name)){
                                        MenuUtil.playClickSound(player);
                                        popSessionData.setCurrentSpawner(treeSpawner);
                                        popSessionData.setCurrentBiome(biome);
                                        PopPage spawner = PopPageType.SPAWNER.getPage();
                                        popSessionData.setCurrent(spawner);
                                        popSessionData.setTransitioning(true);
                                        player.openInventory(spawner.getInventory(popSessionData));
                                        popSessionData.setTransitioning(false);
                                        break;
                                    }
                                }
                            }
                        }catch (Exception e){
                        }
                    }
                }
            }
        }
    }

}
