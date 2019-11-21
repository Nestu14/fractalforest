package com.eclipsekingdom.fractalforest.protection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.redcastlemedia.multitallented.civs.items.ItemManager;
import org.redcastlemedia.multitallented.civs.regions.Region;
import org.redcastlemedia.multitallented.civs.regions.RegionManager;
import org.redcastlemedia.multitallented.civs.regions.RegionType;
import org.redcastlemedia.multitallented.civs.towns.*;
import org.redcastlemedia.multitallented.civs.util.Util;

public class CivsProtection implements IRegionProtector {

    @Override
    public boolean isAllowed(Player player, Location location) {
        return !isProtectedRegion(player, location);
    }


    public boolean isProtectedRegion(Player player, Location location) {
        if (!player.hasPermission("civs.admin") && player.getGameMode() != GameMode.CREATIVE) {
            return shouldBlockAction(location, player, "block_break", "member");
        } else {
            return false;
        }
    }

    static boolean shouldBlockAction(Location location, Player player, String type, String pRole) {
        RegionManager regionManager = RegionManager.getInstance();
        TownManager townManager = TownManager.getInstance();
        Town town = townManager.getTownAt(location);
        outer:
        if (town != null) {
            TownType townType = (TownType) ItemManager.getInstance().getItemType(town.getType());
            if (!townType.getEffects().containsKey(type)) {
                break outer;
            }
            boolean hasPower = town.getPower() > 0;
            boolean hasGrace = hasPower || TownManager.getInstance().hasGrace(town, true);
            if (!hasGrace) {
                break outer;
            }

            if (player == null) {
                return true;
            }
            String role = town.getPeople().get(player.getUniqueId());
            if (role == null || (!role.contains("owner") && pRole != null && !role.contains(pRole))) {
                return true;
            }
        }
        Region region = regionManager.getRegionAt(location);
        if (region == null ||
                !region.getEffects().containsKey(type)) {
            return false;
        }
        if (player == null) {
            return true;
        }
        String role = region.getPeople().get(player.getUniqueId());
        if (role == null) {
            return true;
        }
        if (town != null && !role.contains("foreign")) {
            RegionType regionType = (RegionType) ItemManager.getInstance().getItemType(region.getType());
            Government government = GovernmentManager.getInstance().getGovernment(town.getGovernmentType());
            if (government.getGovernmentType() == GovernmentType.COMMUNISM ||
                    government.getGovernmentType() == GovernmentType.ANARCHY) {
                role = "owner";
            } else if ((government.getGovernmentType() == GovernmentType.SOCIALISM ||
                    government.getGovernmentType() == GovernmentType.DEMOCRATIC_SOCIALISM ||
                    government.getGovernmentType() == GovernmentType.LIBERTARIAN_SOCIALISM) &&
                    (regionType.getGroups().contains("mine") ||
                            regionType.getGroups().contains("quarry") ||
                            regionType.getGroups().contains("farm") ||
                            regionType.getGroups().contains("factory"))) {
                role = "owner";
            }
        }
        if (role.contains("owner")) {
            return false;
        }
        if (Util.equivalentLocations(location, region.getLocation()) &&
                type.equals("block_break")) {
            return true;
        }
        if (pRole == null && (role.contains("ally") || role.contains("member"))) {
            return false;
        }
        if (pRole != null && role.contains(pRole)) {
            return false;
        }
        return true;
    }

}