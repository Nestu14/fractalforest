package com.eclipsekingdom.fractalforest.protection;

import com.eclipsekingdom.playerplot.Permissions;
import com.eclipsekingdom.playerplot.PlayerPlot;
import com.eclipsekingdom.playerplot.data.PlotCache;
import com.eclipsekingdom.playerplot.plot.Plot;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerPlotProtection implements IRegionProtector {

    private PlayerPlot playerPlot;
    private PlotCache plotCache;

    public PlayerPlotProtection(Plugin plugin) {
        assert plugin instanceof PlayerPlot;
        this.playerPlot = (PlayerPlot) plugin;
        this.plotCache = playerPlot.getPlotCache();
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        Plot plot = plotCache.getPlot(location);
        if (plot != null) {
            if (Permissions.canBuildEverywhere(player) || plot.getOwnerID().equals(player.getUniqueId()) || plot.trusts(player)) { //TODO MOVE TO trusts
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
