package com.eclipsekingdom.fractalforest.protection;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IRegionProtector {

    boolean isAllowed(Player player, Location location);

}
