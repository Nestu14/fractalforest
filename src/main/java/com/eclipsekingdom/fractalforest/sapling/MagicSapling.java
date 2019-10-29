package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.PluginConfig;
import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.trees.ITree;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MagicSapling extends BukkitRunnable {

    private int countdown = 3;
    private Location seed;
    private Block saplingBlock;
    private ITree tree;

    public MagicSapling(Player planter, Species species, Location seed) {
        this.seed = seed;
        this.tree = species.getIndividual(planter, seed);
        this.saplingBlock = seed.getBlock();
        runTaskTimer(FractalForest.plugin, 0, 20 * 1);
    }

    @Override
    public void run() {
        if (Tag.SAPLINGS.isTagged(saplingBlock.getType())) {
            if (countdown <= 0) {
                SaplingListener.locationToSapling.remove(saplingBlock.getLocation());
                tree.growPhased(PluginConfig.getPhasePeriod());
                cancel();
            } else {
                seed.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, seed, 9, 0.5,0.7,0.5);
                countdown--;
            }
        } else {
            cancel();
        }
    }

}
