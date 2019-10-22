package com.eclipsekingdom.fractalforest.gui.pop.session;

import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.gui.pop.page.PopPageType;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class LivePopSessions {

    private static HashMap<UUID, PopSessionData> playerToData = new HashMap<>();

    public static void disable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerToData.remove(player.getUniqueId());
            player.closeInventory();
        }
    }

    public static void launch(Player player, TreePopulator populator, boolean initialCreate) {
        end(player);
        UUID playerID = player.getUniqueId();
        PopPage home = PopPageType.HOME.getPage();
        PopSessionData popSessionData = new PopSessionData(home, populator, initialCreate);
        playerToData.put(playerID, popSessionData );
        player.openInventory(home.getInventory(popSessionData));
    }

    public static void end(Player player) {
        UUID playerID = player.getUniqueId();
        PopSessionData popSessionData = playerToData.get(playerID);
        if (popSessionData != null && popSessionData.isInitialCreate()) {
            popSessionData.getPopulator().initialize(player);
        }
        playerToData.remove(playerID);
    }

    public static boolean hasSession(Player player) {
        return playerToData.containsKey(player.getUniqueId());
    }

    public static PopSessionData getData(Player player) {
        return playerToData.get(player.getUniqueId());
    }

}