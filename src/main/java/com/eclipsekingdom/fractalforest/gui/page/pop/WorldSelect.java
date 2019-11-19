package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorldSelect implements PageContents {

    private static Set<World> worlds = buildWorldSet();

    private static Set<World> buildWorldSet() {
        Set<World> worldSet = new HashSet<>();
        for (World world : Bukkit.getWorlds()) {
            worldSet.add(world);
        }
        return worldSet;
    }

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        menu.setItem(4, Icons.createIcon(Material.GRASS_BLOCK, "World Selection"));
        List<World> currentWorlds = pop.getWorlds();

        int index = 10;
        for (World world : worlds) {
            if (!currentWorlds.contains(world)) {
                if (index < 44) {
                    World.Environment environment = world.getEnvironment();
                    Material material;
                    if (environment == World.Environment.THE_END) {
                        material = Material.END_STONE;
                    } else if (environment == World.Environment.NETHER) {
                        material = Material.NETHERRACK;
                    } else {
                        material = Material.GRASS_BLOCK;
                    }
                    menu.setItem(index, Icons.createIcon(material, world.getName()));
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
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        ItemStack itemStack = menu.getItem(slot);
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            ItemMeta meta = itemStack.getItemMeta();
            String name = meta.hasDisplayName() ? meta.getDisplayName() : "";
            World world = Bukkit.getWorld(name);
            if (world != null) {
                TreePopulator pop = popData.getPopulator();
                pop.getWorlds().add(world);
                sessionData.transition(player, PageType.WORLD_OVERVIEW);
            }
        }
    }


}
