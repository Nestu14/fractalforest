package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.util.X.FGlass;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreeSpawner;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TreeOverview implements PageContents {

    private Material writtenBook = XMaterial.WRITABLE_BOOK.parseMaterial();

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        TreeBiome biome = popData.getCurrentBiome();
        List<TreeSpawner> spawners = pop.getBiomeToTreeSpawner().get(biome);

        menu.setItem(4, Icons.createIcon(writtenBook, ChatColor.DARK_GRAY + "Edit Tree Spawners"));
        menu.setItem(8, biome.getItemStack());

        int offset = sessionData.getPageOffsetX();
        int treeSpawnerSize = spawners.size();

        for (int i = 0; i < 7; i++) {
            int index = i + 10;
            if (treeSpawnerSize > i + offset) {
                TreeSpawner treeSpawner = spawners.get(i + offset);
                menu.setItem(index, Icons.createTreeSpawner(treeSpawner));
                menu.setItem(index + 9, Icons.createGlass(MenuGlass.RED, ChatColor.RED + "X"));
            } else {
                if (treeSpawnerSize > i - 1 + offset) {
                    menu.setItem(index, Icons.createGlass(MenuGlass.LIME, ChatColor.GREEN + "+"));
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
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        if (slot == 30) {
            sessionData.scrollLeft(player, this, menu);
        } else if (slot == 32) {
            sessionData.scrollRight(player, this, menu);
        } else {
            ItemStack itemStack = menu.getItem(slot);
            TreePopulator pop = popData.getPopulator();
            if (itemStack != null) {
                if (FGlass.equals(itemStack, MenuGlass.LIME)) {
                    sessionData.transition(player, PageType.TREE_SELECT);
                } else if (FGlass.equals(itemStack, MenuGlass.RED)) {
                    ItemStack treeStack = menu.getItem(slot - 9);
                    if (treeStack != null && treeStack.getType() != Material.AIR) {
                        int index = (slot - 19) + sessionData.getPageOffsetX();
                        ItemMeta meta = treeStack.getItemMeta();
                        String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
                        Species species = Species.from(name);
                        if (species != null) {
                            TreeBiome biome = popData.getCurrentBiome();
                            List<TreeSpawner> spawners = pop.getBiomeToTreeSpawner().get(biome);
                            spawners.remove(index);
                            sessionData.registerEdit();
                        }
                        MenuUtil.playClickSound(player);
                        populate(menu, sessionData);
                    }
                }
            }
        }
    }


}
