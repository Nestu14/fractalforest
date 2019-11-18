package com.eclipsekingdom.fractalforest.protection;

import me.angeschossen.lands.api.enums.LandsAction;
import me.angeschossen.lands.api.landsaddons.LandsAddon;
import me.angeschossen.lands.api.objects.LandChunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LandsProtection implements IRegionProtector {

    private LandsAddon landsAddon;

    public LandsProtection(LandsAddon landsAddon) {
        this.landsAddon = landsAddon;
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        LandChunk landChunk = landsAddon.getLandChunk(location);
        if (landChunk != null) {
            return landChunk.canAction(player.getUniqueId(), LandsAction.BLOCK_PLACE);
        } else {
            return true;
        }
    }
}
