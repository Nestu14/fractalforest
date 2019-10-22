package com.eclipsekingdom.fractalforest.gui.pop;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.gui.pop.session.LivePopSessions;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class PopInputListener implements Listener {

    public PopInputListener() {
        FractalForest plugin = FractalForest.plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {
        Inventory inventory = e.getClickedInventory();
        if (e.getWhoClicked() instanceof Player && inventory != null) {
            Player player = (Player) e.getWhoClicked();
            if (LivePopSessions.hasSession(player)) {
                PopSessionData stormSessionData = LivePopSessions.getData(player);
                PopPage page = stormSessionData.getCurrent();
                e.setCancelled(true);
                if (isMenuClick(inventory, player)) {
                    page.processClick(player, inventory, stormSessionData, e.getSlot());
                }
            }
        }
    }

    private boolean isMenuClick(Inventory clickedInventory, HumanEntity humanEntity) {
        return humanEntity.getOpenInventory().getTopInventory().equals(clickedInventory);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrag(InventoryDragEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            if (LivePopSessions.hasSession(player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player player = (Player) e.getPlayer();
            if (LivePopSessions.hasSession(player)) {
                PopSessionData stormSessionData = LivePopSessions.getData(player);
                if (!stormSessionData.isTransitioning()) {
                    LivePopSessions.end(player);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        if (LivePopSessions.hasSession(player)) {
            LivePopSessions.end(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (LivePopSessions.hasSession(player)) {
            LivePopSessions.end(player);
        }
    }


}
