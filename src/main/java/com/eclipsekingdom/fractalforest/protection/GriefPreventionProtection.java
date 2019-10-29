package com.eclipsekingdom.fractalforest.protection;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GriefPreventionProtection implements IRegionProtector {


    private GriefPrevention griefPrevention;

    public GriefPreventionProtection(Plugin plugin) {
        assert plugin instanceof GriefPrevention;
        this.griefPrevention = GriefPrevention.instance;
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        Claim claim = griefPrevention.dataStore.getClaimAt(location, false, null);
        if (claim != null) {
            return claim.allowBuild(player, Material.OAK_WOOD) == null;
        } else {
            return true;
        }
    }
}
