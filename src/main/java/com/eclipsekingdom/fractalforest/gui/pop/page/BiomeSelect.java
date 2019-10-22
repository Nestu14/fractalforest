package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

public class BiomeSelect extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {
        TreePopulator pop = popSessionData.getPopulator();
        menu.setItem(4, Icons.createIcon(Material.GRASS_BLOCK, "World Selection"));
        Set<Biome> currentBiomes = pop.getBiomeToTreeSpawner().keySet();

        int index = 10;
        for (Biome biome : Biome.values()) {
            if (!currentBiomes.contains(biome)) {
                if (index < 44) {
                    menu.setItem(index, Icons.createBiome(biome));
                }
                if ((index + 2) % 9 == 0) {
                    index += 3;
                } else {
                    index++;
                }
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
    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        ItemStack itemStack = menu.getItem(slot);
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            ItemMeta meta = itemStack.getItemMeta();
            String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
            try {
                Biome biome = Biome.valueOf(name);
                MenuUtil.playClickSound(player);
                TreePopulator pop = popSessionData.getPopulator();
                pop.getBiomeToTreeSpawner().put(biome, TreeSpawner.defaultTreeSpawners());
                PopPage overview = PopPageType.BIOME_OVERVIEW.getPage();
                popSessionData.setCurrent(overview);
                popSessionData.setTransitioning(true);
                player.openInventory(overview.getInventory(popSessionData));
                popSessionData.setTransitioning(false);
            } catch (Exception e) {
            }
        }
    }

}
