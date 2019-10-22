package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BiomeOverview extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {
        TreePopulator pop = popSessionData.getPopulator();
        List<Biome> biomes = new ArrayList<>();
        for(Biome biome : pop.getBiomeToTreeSpawner().keySet()){
            biomes.add(biome);
        }
        menu.setItem(4, Icons.createIcon(Material.WRITABLE_BOOK, ChatColor.DARK_GRAY + "Edit Biomes"));

        int offset = popSessionData.getPageOffsetX();
        int biomesSize = biomes.size();

        for (int i = 0; i < 7; i++) {
            int index = i + 10;
            if (biomesSize > i + offset) {
                Biome biome = biomes.get(i + offset);
                menu.setItem(index, Icons.createBiome(biome));
                if (biomesSize > 1) {
                    menu.setItem(index + 9, Icons.createIcon(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "X"));
                } else {
                    menu.setItem(index + 9, Icons.BACKGROUND_ITEM);
                }
            } else {
                if (biomesSize > i - 1 + offset) {
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
                    PopPage select = PopPageType.BIOME_SELECT.getPage();
                    popSessionData.setTransitioning(true);
                    player.openInventory(select.getInventory(popSessionData));
                    popSessionData.setTransitioning(false);
                    popSessionData.setCurrent(select);
                } else if (material == Material.RED_STAINED_GLASS_PANE) {
                    ItemStack biomeStack = menu.getItem(slot - 9);
                    if (biomeStack != null && biomeStack.getType() != Material.AIR) {
                        ItemMeta meta = biomeStack.getItemMeta();
                        String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
                        Biome biome = Biome.valueOf(name);
                        MenuUtil.playClickSound(player);
                        pop.getBiomeToTreeSpawner().remove(biome);
                        populate(menu, popSessionData);
                    }
                }
            }
        }
    }



}
