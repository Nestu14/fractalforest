package com.eclipsekingdom.fractalforest.gui.page.pop;

import com.eclipsekingdom.fractalforest.gui.*;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import static com.eclipsekingdom.fractalforest.util.language.Message.STATUS_TPOP_DISABLED;
import static com.eclipsekingdom.fractalforest.util.language.Message.STATUS_TPOP_ENABLED;

public class Home implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        PopData popData = sessionData.getPopData();
        TreePopulator pop = popData.getPopulator();
        menu.setItem(4, Icons.createIcon(Material.NAME_TAG, ChatColor.DARK_GRAY + pop.getName()));
        menu.setItem(10, Icons.BACKGROUND_ITEM);
        menu.setItem(11, Icons.createIcon(Material.GRASS_BLOCK, ChatColor.DARK_PURPLE + "Worlds"));
        menu.setItem(12, Icons.BACKGROUND_ITEM);
        menu.setItem(13, Icons.createIcon(Material.WHEAT_SEEDS, ChatColor.GREEN + "Generation"));
        menu.setItem(14, Icons.BACKGROUND_ITEM);
        boolean enabled = pop.isEnabled();
        Material material = enabled ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
        String label = enabled ? ChatColor.GREEN + STATUS_TPOP_ENABLED.toString() : ChatColor.RED + STATUS_TPOP_DISABLED.toString();
        menu.setItem(15, Icons.createIcon(material, label));
        menu.setItem(16, Icons.BACKGROUND_ITEM);
        menu.setItem(22, Icons.CLOSE);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PopData popData = sessionData.getPopData();
        if (slot == 22) {
            LiveSessions.end(player);
            player.closeInventory();
            MenuUtil.playClickSound(player);
        } else if (slot == 15) {
            MenuUtil.playClickSound(player);
            TreePopulator pop = popData.getPopulator();
            pop.setEnabled(!pop.isEnabled());
            populate(menu, sessionData);
        } else if (slot == 11 || slot == 13) {
            PageType to = slot == 11 ? PageType.WORLD_OVERVIEW : PageType.GENERATION;
            sessionData.transition(player, to);
        }
    }

}
