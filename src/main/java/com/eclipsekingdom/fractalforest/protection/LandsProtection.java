package com.eclipsekingdom.fractalforest.protection;

import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.LandChunk;
import me.angeschossen.lands.api.role.enums.RoleSetting;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LandsProtection implements IRegionProtector {

    private LandsIntegration landsAddon;

    public LandsProtection(LandsIntegration landsAddon) {
        this.landsAddon = landsAddon;
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        LandChunk landChunk = landsAddon.getLandChunk(location);
        if (landChunk != null) {
            return landChunk.canAction(player.getUniqueId(), RoleSetting.BLOCK_PLACE);
        } else {
            return true;
        }
    }
}
