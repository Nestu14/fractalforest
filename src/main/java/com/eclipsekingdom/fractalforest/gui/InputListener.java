package com.eclipsekingdom.fractalforest.gui;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.gui.page.Page;
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

public class InputListener implements Listener {

    public InputListener() {
        FractalForest plugin = FractalForest.plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {
        Inventory inventory = e.getClickedInventory();
        Player player = (Player) e.getWhoClicked();
        if (e.getWhoClicked() instanceof Player && inventory != null && isMenuClick(inventory, player)) {
            if (LiveSessions.hasSession(player)) {
                e.setCancelled(true);
                SessionData sessionData = LiveSessions.getData(player);
                Page page = sessionData.getCurrent();
                page.processClick(player, inventory, sessionData, e.getSlot());
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
            if (LiveSessions.hasSession(player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player player = (Player) e.getPlayer();
            if (LiveSessions.hasSession(player)) {
                SessionData sessionData = LiveSessions.getData(player);
                if (!sessionData.isTransitioning()) {
                    LiveSessions.end(player);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        if (LiveSessions.hasSession(player)) {
            LiveSessions.end(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (LiveSessions.hasSession(player)) {
            LiveSessions.end(player);
        }
    }


}
