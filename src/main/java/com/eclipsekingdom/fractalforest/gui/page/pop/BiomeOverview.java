package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
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

public class BiomeOverview implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        List<Biome> biomes = new ArrayList<>();
        for (Biome biome : pop.getBiomeToTreeSpawner().keySet()) {
            biomes.add(biome);
        }
        menu.setItem(4, Icons.createIcon(Material.WRITABLE_BOOK, ChatColor.DARK_GRAY + "Edit Biomes"));

        int offset = sessionData.getPageOffsetX();
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
        menu.setItem(31, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetX()));
        menu.setItem(32, Icons.createIcon(Material.ARROW, "Scroll Right"));

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot) {
        PopData popData = sessionData.getPopData();
        if (slot == 30) {
            MenuUtil.playClickSound(player);
            sessionData.scrollLeft();
            populate(menu, sessionData);
        } else if (slot == 32) {
            MenuUtil.playClickSound(player);
            sessionData.scrollRight();
            populate(menu, sessionData);
        } else {
            ItemStack itemStack = menu.getItem(slot);
            TreePopulator pop = popData.getPopulator();
            if (itemStack != null) {
                Material material = itemStack.getType();
                if (material == Material.LIME_STAINED_GLASS_PANE) {
                    sessionData.transition(player, PageType.BIOME_SELECT);
                } else if (material == Material.RED_STAINED_GLASS_PANE) {
                    ItemStack biomeStack = menu.getItem(slot - 9);
                    if (biomeStack != null && biomeStack.getType() != Material.AIR) {
                        ItemMeta meta = biomeStack.getItemMeta();
                        String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
                        Biome biome = Biome.valueOf(name);
                        MenuUtil.playClickSound(player);
                        pop.getBiomeToTreeSpawner().remove(biome);
                        populate(menu, sessionData);
                    }
                }
            }
        }
    }


}
