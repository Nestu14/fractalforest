package com.eclipsekingdom.fractalforest.gui.page.gen;

import com.eclipsekingdom.fractalforest.gen.Generator;
import com.eclipsekingdom.fractalforest.gen.WorldData;
import com.eclipsekingdom.fractalforest.gui.GenData;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

import static com.eclipsekingdom.fractalforest.util.language.Message.LABEL_DISABLED;
import static com.eclipsekingdom.fractalforest.util.language.Message.LABEL_ENABLED;

public class GeneratorHome implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        menu.setItem(4, Icons.createIcon(Material.CRAFTING_TABLE, ChatColor.DARK_PURPLE + "Generator"));
        List<World> worlds = Bukkit.getWorlds();
        int worldsSize = worlds.size();
        int worldIndex = 0;

        int offsetY = sessionData.getPageOffsetY();
        for (int row = 1; row < 5; row++) {
            int index = 9 * row;
            if (worldIndex + offsetY < worldsSize) {
                World world = worlds.get(worldIndex + offsetY);
                menu.setItem(index + 1, Icons.BACKGROUND_ITEM);
                menu.setItem(index + 2, Icons.createIcon(getMaterial(world.getEnvironment()), world.getName()));
                WorldData worldData = Generator.getWorldData(world);
                if (worldData.hasTreePopulator()) {
                    menu.setItem(index + 3, Icons.createIcon(Material.WHEAT_SEEDS, worldData.getTreePopulator().getName()));
                } else {
                    menu.setItem(index + 3, Icons.createIcon(Material.BARRIER, "None"));
                }
                boolean enabled = worldData.isEnabled();
                ItemStack enabledStack = enabled ? Icons.createIcon(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + LABEL_ENABLED.toString()) :
                        Icons.createIcon(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + LABEL_DISABLED.toString());
                menu.setItem(index + 4, enabledStack);
                menu.setItem(index + 5, enabledStack);
                menu.setItem(index + 6, enabledStack);
                menu.setItem(index + 7, Icons.BACKGROUND_ITEM);
            } else {
                for (int i = 1; i < 8; i++) {
                    menu.setItem(index + i, Icons.BACKGROUND_ITEM);
                }
            }

            worldIndex++;
        }


        menu.setItem(17, Icons.createIcon(Material.TRIPWIRE_HOOK, "Scroll Up"));
        menu.setItem(26, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetY()));
        menu.setItem(35, Icons.createIcon(Material.HOPPER, "Scroll Down"));

        menu.setItem(49, Icons.CLOSE);

        return menu;
    }


    private Material getMaterial(World.Environment environment) {
        if (environment == World.Environment.THE_END) {
            return Material.END_STONE;
        } else if (environment == World.Environment.NETHER) {
            return Material.NETHERRACK;
        } else {
            return Material.GRASS_BLOCK;
        }
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        ItemStack itemStack = menu.getItem(slot);
        if (slot == 49) {
            LiveSessions.end(player);
            player.closeInventory();
            MenuUtil.playClickSound(player);
        } else if (slot == 17) {
            sessionData.scrollUp(player, this, menu);
        } else if (slot == 35) {
            sessionData.scrollDown(player, this, menu);
        } else {
            World world = getWorld(menu, slot);
            if (world != null) {
                if ((slot - 3) % 9 == 0) {
                    GenData genData = sessionData.getGenData();
                    genData.setCurrentWorld(world);
                    sessionData.transition(player, PageType.POP_SELECT);
                } else if (itemStack != null && enabledMaterial.contains(itemStack.getType())) {
                    WorldData worldData = Generator.getWorldData(world);
                    worldData.toggleEnabled(world);
                    populate(menu, sessionData);
                }
            }
        }
    }

    private World getWorld(Inventory menu, int slot) {
        ItemStack worldItem = menu.getItem((slot / 9)*9 + 2);
        if (worldItem.hasItemMeta() && worldItem.getItemMeta().hasDisplayName()) {
            return Bukkit.getWorld(worldItem.getItemMeta().getDisplayName());
        } else {
            return null;
        }
    }

    private Set<Material> enabledMaterial = new ImmutableSet.Builder<Material>()
            .add(Material.LIME_STAINED_GLASS_PANE).add(Material.RED_STAINED_GLASS_PANE).build();


}