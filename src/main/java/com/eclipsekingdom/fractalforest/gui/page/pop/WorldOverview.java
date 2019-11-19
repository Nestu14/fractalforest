package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class WorldOverview implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        List<World> worlds = pop.getWorlds();
        menu.setItem(4, Icons.createIcon(Material.GRASS_BLOCK, ChatColor.DARK_PURPLE + "Worlds"));

        int offset = sessionData.getPageOffsetX();
        int worldsSize = worlds.size();

        for (int i = 0; i < 7; i++) {
            int index = i + 10;
            if (worldsSize > i + offset) {
                World world = worlds.get(i + offset);
                Environment environment = world.getEnvironment();
                Material material;
                if (environment == Environment.THE_END) {
                    material = Material.END_STONE;
                } else if (environment == Environment.NETHER) {
                    material = Material.NETHERRACK;
                } else {
                    material = Material.GRASS_BLOCK;
                }
                menu.setItem(index, Icons.createIcon(material, world.getName()));
                if (worldsSize > 1) {
                    menu.setItem(index + 9, Icons.createIcon(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "X"));
                } else {
                    menu.setItem(index + 9, Icons.BACKGROUND_ITEM);
                }
            } else {
                if (worldsSize > i - 1 + offset) {
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
                    sessionData.transition(player, PageType.WORLD_SELECT);
                } else if (material == Material.RED_STAINED_GLASS_PANE) {
                    ItemStack worldTypeStack = menu.getItem(slot - 9);
                    if (worldTypeStack != null && worldTypeStack.getType() != Material.AIR) {
                        ItemMeta meta = worldTypeStack.getItemMeta();
                        String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
                        World world = Bukkit.getWorld(name);
                        if (world != null) {
                            MenuUtil.playClickSound(player);
                            pop.getWorlds().remove(world);
                            populate(menu, sessionData);
                        }
                    }
                }
            }
        }
    }


}
