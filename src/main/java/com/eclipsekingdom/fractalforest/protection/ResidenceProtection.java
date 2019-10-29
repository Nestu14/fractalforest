package com.eclipsekingdom.fractalforest.protection;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ResidenceProtection implements IRegionProtector {

    public ResidenceProtection(Plugin plugin) {
        assert plugin instanceof Residence;
        FlagPermissions.addFlag("build");
    }

    @Override
    public boolean isAllowed(Player player, Location location) {
        ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(location);
        if (res != null) {
            ResidencePermissions perms = res.getPermissions();
            return perms.playerHas(player, Flags.getFlag("build"), true);
        } else {
            return true;
        }
    }


}
