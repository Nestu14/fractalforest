package com.eclipsekingdom.fractalforest;

import org.bukkit.entity.Player;

public class Permissions {

    private static final String SAPLING_PERM = "forest.sapling";
    private static final String POP_PERM = "forest.pop";


    public static boolean canSummonSapling(Player player){
        return hasPermission(player, SAPLING_PERM);
    }

    public static boolean canEditPop(Player player){
        return hasPermission(player, POP_PERM);
    }

    private static boolean hasPermission(Player player, String permString){
        return (player.hasPermission("forest.*") || player.hasPermission(permString));
    }

}
