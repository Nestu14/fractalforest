package com.eclipsekingdom.fractalforest.protection;

import com.eclipsekingdom.playerplot.PlayerPlot;
import com.eclipsekingdom.playerplot.PlayerPlotAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerPlotProtection implements IRegionProtector {

    PlayerPlotAPI playerPlotAPI = PlayerPlotAPI.getInstance();

    public PlayerPlotProtection(Plugin plugin) {
        assert plugin instanceof PlayerPlot;
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        return playerPlotAPI.hasAccessAt(player, location);
    }
}
