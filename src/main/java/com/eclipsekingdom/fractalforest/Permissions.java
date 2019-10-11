package com.eclipsekingdom.fractalforest;

import org.bukkit.entity.Player;

public class Permissions {

    private static final String SAPLING_PERM = "forest.sapling";


    public static boolean canSummonSapling(Player player){
        return hasPermission(player, SAPLING_PERM);
    }

    private static boolean hasPermission(Player player, String permString){
        return (player.hasPermission("forest.*") || player.hasPermission(permString));
    }

}
