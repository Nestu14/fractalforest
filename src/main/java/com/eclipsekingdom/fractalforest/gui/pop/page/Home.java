package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.Icons;
import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.session.LivePopSessions;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Home extends PopPageContents {

    @Override
    public Inventory populate(Inventory menu, PopSessionData popSessionData) {
        TreePopulator pop = popSessionData.getPopulator();
        menu.setItem(4, Icons.createIcon(Material.NAME_TAG, ChatColor.DARK_GRAY + pop.getName()));
        menu.setItem(10, Icons.BACKGROUND_ITEM);
        menu.setItem(11, Icons.createIcon(Material.GRASS_BLOCK, ChatColor.DARK_PURPLE + "Worlds"));
        menu.setItem(12, Icons.BACKGROUND_ITEM);
        menu.setItem(13, Icons.createIcon(Material.WHEAT_SEEDS, ChatColor.GREEN + "Generation"));
        menu.setItem(14, Icons.BACKGROUND_ITEM);
        boolean enabled = pop.isEnabled();
        Material material = enabled? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
        String label = enabled? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled";
        menu.setItem(15, Icons.createIcon(material, label));
        menu.setItem(16, Icons.BACKGROUND_ITEM);
        menu.setItem(22, Icons.CLOSE);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        if (slot == 22) {
            LivePopSessions.end(player);
            player.closeInventory();
            MenuUtil.playClickSound(player);
        } else if (slot == 15){
            MenuUtil.playClickSound(player);
            TreePopulator pop = popSessionData.getPopulator();
            pop.setEnabled(!pop.isEnabled());
            populate(menu, popSessionData);
        }else if(slot == 11 || slot == 13){
            MenuUtil.playClickSound(player);
            PopPage to = slot == 11? PopPageType.WORLD_OVERVIEW.getPage() : PopPageType.GENERATION.getPage();
            popSessionData.setCurrent(to);
            popSessionData.setTransitioning(true);
            player.openInventory(to.getInventory(popSessionData));
            popSessionData.setTransitioning(false);
        }
    }

}
