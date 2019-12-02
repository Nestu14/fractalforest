package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.phylo.Species;
import org.bukkit.command.CommandSender;

public class Permissions {

    private static final String SAPLING_PERM = "forest.sapling";
    private static final String PLANTER_PERM = "forest.plant.*";
    private static final String POP_PERM = "forest.pop";
    private static final String GEN_PERM = "forest.gen";
    private static final String ENCYCLOPEDIA_PERM = "forest.encyclopedia";

    public static boolean canSummonSapling(CommandSender sender) {
        return hasPermission(sender, SAPLING_PERM);
    }

    public static boolean canEditPop(CommandSender sender) {
        return hasPermission(sender, POP_PERM);
    }

    public static boolean canEditGen(CommandSender sender) {
        return hasPermission(sender, GEN_PERM);
    }

    public static boolean canPlant(CommandSender sender, Species species) {
        return sender.hasPermission(PLANTER_PERM) || hasPermission(sender, species.getPlanterPerm());
    }

    public static boolean canWriteEncyclopedia(CommandSender sender) {
        return hasPermission(sender, ENCYCLOPEDIA_PERM);
    }

    private static boolean hasPermission(CommandSender sender, String permString) {
        return (sender.hasPermission("forest.*") || sender.hasPermission(permString));
    }

}
