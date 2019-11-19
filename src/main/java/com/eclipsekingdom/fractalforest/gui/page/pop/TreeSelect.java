package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TreeSelect implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        menu.setItem(4, Icons.createIcon(Material.OAK_SAPLING, "Tree Selection"));
        Biome biome = popData.getCurrentBiome();
        menu.setItem(8, Icons.createBiome(biome));

        List<TreeSpawner> currentSpawners = pop.getBiomeToTreeSpawner().get(biome);

        int index = 10;
        for (Species species : Species.values()) {
            if (!containsSpecies(currentSpawners, species)) {
                if (index < 44) {
                    menu.setItem(index, Icons.createTreeSpawnerType(TreeSpawner.defaultTreeSpawner(species)));
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

    private boolean containsSpecies(List<TreeSpawner> spawners, Species species) {
        for (TreeSpawner spawner : spawners) {
            if (spawner.getSpecies() == species) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot) {
        PopData popData = sessionData.getPopData();
        ItemStack itemStack = menu.getItem(slot);
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            ItemMeta meta = itemStack.getItemMeta();
            String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
            try {
                Species species = Species.valueOf(name);
                if (species != null) {
                    TreeSpawner treeSpawner = TreeSpawner.defaultTreeSpawner(species);
                    Biome biome = popData.getCurrentBiome();
                    popData.getPopulator().getBiomeToTreeSpawner().get(biome).add(treeSpawner);
                    sessionData.transition(player, PageType.TREE_OVERVIEW);
                }
            } catch (Exception e) {
            }
        }
    }
}
