package com.eclipsekingdom.fractalforest.gui;

import com.eclipsekingdom.fractalforest.gui.page.Page;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import com.eclipsekingdom.fractalforest.populator.PopCache;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LiveSessions {

    private static HashMap<UUID, SessionData> playerToData = new HashMap<>();

    public static void disable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerToData.remove(player.getUniqueId());
            player.closeInventory();
        }
    }

    public static void launchPop(Player player, TreePopulator populator, boolean initialCreate) {
        end(player);
        UUID playerID = player.getUniqueId();
        Page home = PageType.HOME.getPage();
        SessionData sessionData = new SessionData(home);
        PopData popData = new PopData(populator, initialCreate);
        sessionData.setPopData(popData);
        playerToData.put(playerID, sessionData);
        player.openInventory(home.getInventory(sessionData));
    }

    public static void launchSpecies(Player player) {
        end(player);
        UUID playerID = player.getUniqueId();
        Page home = PageType.SAPLING_OVERVIEW.getPage();
        SessionData sessionData = new SessionData(home);
        playerToData.put(playerID, sessionData);
        player.openInventory(home.getInventory(sessionData));
    }

    public static void end(Player player) {
        UUID playerID = player.getUniqueId();
        if (playerToData.containsKey(playerID)) {
            SessionData sessionData = playerToData.get(playerID);
            PopData popData = sessionData.getPopData();
            if (popData != null) {
                if (popData.isInitialCreate()) popData.getPopulator().initialize(player);
                PopCache.save();
            }
            playerToData.remove(playerID);
        }
    }

    public static boolean hasSession(Player player) {
        return playerToData.containsKey(player.getUniqueId());
    }

    public static SessionData getData(Player player) {
        return playerToData.get(player.getUniqueId());
    }


    public static boolean isBusyPopBusy(String popName) {
        for (SessionData data : playerToData.values()) {
            if (data.getPopData() != null && data.getPopData().getPopulator().getName().equalsIgnoreCase(popName)) {
                return true;
            }
        }
        return false;
    }

    public static String getPopEditor(String popName) {
        for (Map.Entry<UUID, SessionData> entry : playerToData.entrySet()) {
            SessionData data = entry.getValue();
            if (data.getPopData() != null && data.getPopData().getPopulator().getName().equalsIgnoreCase(popName)) {
                return getPlayerName(entry.getKey());
            }
        }
        return "";
    }

    private static String getPlayerName(UUID playerID) {
        Player player = Bukkit.getPlayer(playerID);
        if (player != null) {
            return player.getName();
        } else {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerID);
            if (offlinePlayer != null) {
                return offlinePlayer.getName();
            } else {
                return "";
            }
        }
    }

}