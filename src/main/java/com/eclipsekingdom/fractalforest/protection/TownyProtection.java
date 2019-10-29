package com.eclipsekingdom.fractalforest.protection;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TownyProtection implements IRegionProtector {

    public TownyProtection(Plugin plugin) {
        assert plugin instanceof Towny;
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        Block block = location.getBlock();
        return PlayerCacheUtil.getCachePermission(player, location, block.getType(), TownyPermission.ActionType.BUILD);
    }
}
