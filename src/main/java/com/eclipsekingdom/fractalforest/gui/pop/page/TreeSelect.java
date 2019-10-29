package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
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

public class TreeSelect extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {
        TreePopulator pop = popSessionData.getPopulator();
        menu.setItem(4, Icons.createIcon(Material.OAK_SAPLING, "Tree Selection"));
        Biome biome = popSessionData.getCurrentBiome();
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

    private boolean containsSpecies(List<TreeSpawner> spawners, Species species){
        for(TreeSpawner spawner: spawners){
            if(spawner.getSpecies() == species){
                return true;
            }
        }
        return false;
    }

    @Override
    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        ItemStack itemStack = menu.getItem(slot);
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            ItemMeta meta = itemStack.getItemMeta();
            String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
            try {
                Species species = Species.valueOf(name);
                if(species != null){
                    TreeSpawner treeSpawner = TreeSpawner.defaultTreeSpawner(species);
                    Biome biome = popSessionData.getCurrentBiome();
                    popSessionData.getPopulator().getBiomeToTreeSpawner().get(biome).add(treeSpawner);
                    MenuUtil.playClickSound(player);
                    PopPage overview = PopPageType.TREE_OVERVIEW.getPage();
                    popSessionData.setCurrent(overview);
                    popSessionData.setTransitioning(true);
                    player.openInventory(overview.getInventory(popSessionData));
                    popSessionData.setTransitioning(false);
                }
            } catch (Exception e) {
            }
        }
    }
}
