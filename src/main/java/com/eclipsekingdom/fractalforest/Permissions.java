package com.eclipsekingdom.fractalforest;

import org.bukkit.entity.Player;

public class Permissions {

    private static final String SAPLING_PERM = "forest.sapling";
    private static final String POP_PERM = "forest.pop";
    private static final String GEN_PERM = "forest.gen";


    public static boolean canSummonSapling(Player player) {
        return hasPermission(player, SAPLING_PERM);
    }

    public static boolean canEditPop(Player player) {
        return hasPermission(player, POP_PERM);
    }

    public static boolean canEditGen(Player player) {
        return hasPermission(player, GEN_PERM);
    }

    private static boolean hasPermission(Player player, String permString) {
        return (player.hasPermission("forest.*") || player.hasPermission(permString));
    }

}
