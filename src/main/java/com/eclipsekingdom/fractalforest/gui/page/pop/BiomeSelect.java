package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.PopData;
import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreeSpawner;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public class BiomeSelect implements PageContents {

    private static Material grassBlock = XMaterial.GRASS_BLOCK.parseMaterial();

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        menu.setItem(4, Icons.createIcon(grassBlock, LABEL_BIOME_SELECTION.toString()));
        Set<TreeBiome> currentBiomes = pop.getBiomeToTreeSpawner().keySet();


        int offsetY = sessionData.getPageOffsetY();
        List<TreeBiome> biomes = new ArrayList<>();
        for (TreeBiome biome : TreeBiome.getSupportedBiomes()) {
            if (!currentBiomes.contains(biome) && biome != TreeBiome.NONE) {
                biomes.add(biome);
            }
        }

        int index = 10;
        for (int i = 0; i < 28; i++) {
            int biomeIndex = i + (7 * offsetY);
            if (biomeIndex < biomes.size()) {
                menu.setItem(index, biomes.get(biomeIndex).getItemStack());
            } else {
                menu.setItem(index, Icons.BACKGROUND_ITEM);
            }
            index += ((index + 2) % 9 == 0 ? 3 : 1);
        }

        menu.setItem(17, Icons.createIcon(Material.TRIPWIRE_HOOK, MENU_SCROLL_UP.toString()));
        menu.setItem(26, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetY()));
        menu.setItem(35, Icons.createIcon(Material.HOPPER, MENU_SCROLL_DOWN.toString()));

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
                    TreeBiome biome = TreeBiome.valueOf(name);
                    TreePopulator pop = popData.getPopulator();
                    pop.getBiomeToTreeSpawner().put(biome, TreeSpawner.defaultTreeSpawners());
                    sessionData.registerEdit();
                    sessionData.transition(player, PageType.BIOME_OVERVIEW);
                } catch (Exception e) {
                }
            }
        }
    }

}
