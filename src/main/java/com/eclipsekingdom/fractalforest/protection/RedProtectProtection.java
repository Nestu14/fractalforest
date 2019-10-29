package com.eclipsekingdom.fractalforest.protection;

import br.net.fabiozumbi12.RedProtect.Bukkit.API.RedProtectAPI;
import br.net.fabiozumbi12.RedProtect.Bukkit.RedProtect;
import br.net.fabiozumbi12.RedProtect.Bukkit.Region;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RedProtectProtection implements IRegionProtector {

    private RedProtectAPI redProtectAPI;

    public RedProtectProtection(Plugin plugin) {
        assert plugin instanceof RedProtect;
        redProtectAPI = ((RedProtect) plugin).getAPI();
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        Region region = redProtectAPI.getRegion(location);
        if (region != null) {
            return region.canBuild(player);
        } else {
            return true;
        }
    }
}
