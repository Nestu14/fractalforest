package com.eclipsekingdom.fractalforest.protection;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WorldGuardProtection implements IRegionProtector {

    private WorldGuardPlugin worldGuardPlugin;

    public WorldGuardProtection(Plugin plugin) {
        assert plugin instanceof WorldGuardPlugin;
        this.worldGuardPlugin = (WorldGuardPlugin) plugin;
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        return worldGuardPlugin.createProtectionQuery().testBlockBreak(player, location.getBlock());
    }
}
