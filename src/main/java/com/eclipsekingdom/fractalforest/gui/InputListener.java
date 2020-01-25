package com.eclipsekingdom.fractalforest.gui;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.gui.page.Page;
import org.bukkit.Material;
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
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class InputListener implements Listener {

    public InputListener() {
        Plugin plugin = FractalForest.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {
        Inventory inventory = e.getClickedInventory();
        Player player = (Player) e.getWhoClicked();
        if (e.getWhoClicked() instanceof Player && inventory != null && LiveSessions.hasSession(player)) {
            if (isMenuClick(inventory, player)) {
                e.setCancelled(true);
                SessionData sessionData = LiveSessions.getData(player);
                Page page = sessionData.getCurrent();
                page.processClick(player, inventory, sessionData, e.getSlot(), e.getClick());
            } else if (e.getClick().isShiftClick() && e.getCurrentItem() != null) {
                SessionData sessionData = LiveSessions.getData(player);
                if (sessionData.isItemPicker()) {
                    e.setCancelled(true);
                    e.setCurrentItem(new ItemStack(Material.AIR));
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
            if (LiveSessions.hasSession(player)) {
                InventoryView view = e.getView();
                Inventory top = view.getTopInventory();
                int topSize = top.getSize();
                for (int slot : e.getRawSlots()) {
                    if (slot < topSize) {
                        e.setCancelled(true);
                        return;
                    }
                }
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
