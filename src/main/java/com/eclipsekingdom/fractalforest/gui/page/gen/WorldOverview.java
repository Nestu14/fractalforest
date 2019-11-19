package com.eclipsekingdom.fractalforest.gui.page.gen;

import com.eclipsekingdom.fractalforest.gen.Generator;
import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WorldOverview implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        /*
        List<World> worlds = new ArrayList<>();
        for (World world : Generator.getWorlds()) {
            worlds.add(world);
        }
        menu.setItem(4, Icons.createIcon(Material.GRASS_BLOCK, ChatColor.DARK_PURPLE + "Worlds"));

        int offset = sessionData.getPageOffsetX();
        int worldsSize = worlds.size();

        for (int i = 0; i < 7; i++) {
            int index = i + 10;
            if (worldsSize > i + offset) {
                World world = worlds.get(i + offset);
                Environment environment = world.getEnvironment();
                Material material = getMaterial(environment);
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
        */
        return menu;
    }

    private Material getMaterial(Environment environment) {
        if (environment == Environment.THE_END) {
            return Material.END_STONE;
        } else if (environment == Environment.NETHER) {
            return Material.NETHERRACK;
        } else {
            return Material.GRASS_BLOCK;
        }
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        if (slot == 30) {
            sessionData.scrollLeft(player, this, menu);
        } else if (slot == 32) {
            sessionData.scrollRight(player, this, menu);
        } else {
            ItemStack itemStack = menu.getItem(slot);
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
                            //Generator.removeWorld(world);
                            populate(menu, sessionData);
                        }
                    }
                }
            }
        }
    }


}
