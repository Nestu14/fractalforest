package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.sys.config.PluginConfig;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.trees.ITree;
import org.bukkit.*;
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
        runTaskTimer(FractalForest.getPlugin(), 0, 20 * 1);
    }

    @Override
    public void run() {
        if (Tag.SAPLINGS.isTagged(saplingBlock.getType()) || saplingBlock.getType() == Material.NETHER_WART) {
            if (countdown <= 0) {
                saplingBlock.setType(Material.AIR);
                SaplingListener.locationToSapling.remove(saplingBlock.getLocation());
                tree.growPhased(PluginConfig.getPhasePeriod());
                cancel();
            } else {
                if(saplingBlock.getType() == Material.NETHER_WART){
                    seed.getWorld().spawnParticle(Particle.REDSTONE, seed, 9, 0.5, 0.7, 0.5, new Particle.DustOptions(Color.RED, 0.77f));
                }else{
                    seed.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, seed, 9, 0.5, 0.7, 0.5);
                }

                countdown--;
            }
        } else {
            cancel();
        }
    }

}
