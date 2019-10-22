package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
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

public class WorldOverview extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {
        TreePopulator pop = popSessionData.getPopulator();
        List<World> worlds = pop.getWorlds();
        menu.setItem(4, Icons.createIcon(Material.GRASS_BLOCK, ChatColor.DARK_PURPLE + "Worlds"));

        int offset = popSessionData.getPageOffsetX();
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
                    PopPage select = PopPageType.WORLD_SELECT.getPage();
                    popSessionData.setTransitioning(true);
                    player.openInventory(select.getInventory(popSessionData));
                    popSessionData.setTransitioning(false);
                    popSessionData.setCurrent(select);
                } else if (material == Material.RED_STAINED_GLASS_PANE) {
                    ItemStack worldTypeStack = menu.getItem(slot - 9);
                    if (worldTypeStack != null && worldTypeStack.getType() != Material.AIR) {
                        ItemMeta meta = worldTypeStack.getItemMeta();
                        String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
                        World world = Bukkit.getWorld(name);
                        if (world != null) {
                            MenuUtil.playClickSound(player);
                            pop.getWorlds().remove(world);
                            populate(menu, popSessionData);
                        }
                    }
                }
            }
        }
    }


}
