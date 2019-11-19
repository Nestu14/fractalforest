package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.PopData;
import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BiomeSelect implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        menu.setItem(4, Icons.createIcon(Material.GRASS_BLOCK, "Biome Selection"));
        Set<Biome> currentBiomes = pop.getBiomeToTreeSpawner().keySet();


        int offsetY = sessionData.getPageOffsetY();
        List<Biome> biomes = new ArrayList<>();
        for (Biome biome : Biome.values()) {
            if (!currentBiomes.contains(biome)) {
                biomes.add(biome);
            }
        }

        int index = 10;
        for (int i = 0; i < 28; i++) {
            int biomeIndex = i + (7 * offsetY);
            if (biomeIndex < biomes.size() ) {
                menu.setItem(index, Icons.createBiome(biomes.get(biomeIndex)));
            } else {
                menu.setItem(index, Icons.BACKGROUND_ITEM);
            }
            index += ((index + 2) % 9 == 0 ? 3 : 1);
        }

        menu.setItem(17, Icons.createIcon(Material.TRIPWIRE_HOOK, "Scroll Up"));
        menu.setItem(26, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetY()));
        menu.setItem(35, Icons.createIcon(Material.HOPPER, "Scroll Down"));

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        if (slot == 17) {
            sessionData.scrollUp(player, this, menu);
        } else if (slot == 35) {
            sessionData.scrollDown(player, this, menu);
        } else {
            ItemStack itemStack = menu.getItem(slot);
            if (itemStack != null && itemStack.getType() != Material.AIR) {
                ItemMeta meta = itemStack.getItemMeta();
                String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
                try {
                    Biome biome = Biome.valueOf(name);
                    TreePopulator pop = popData.getPopulator();
                    pop.getBiomeToTreeSpawner().put(biome, TreeSpawner.defaultTreeSpawners());
                    sessionData.transition(player, PageType.BIOME_OVERVIEW);
                } catch (Exception e) {
                }
            }
        }
    }

}
