package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.sys.config.PluginConfig;
import com.eclipsekingdom.fractalforest.trees.ITree;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.eclipsekingdom.fractalforest.util.math.TreeMath;
import com.google.common.collect.ImmutableSet;
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
        if (MagicSapling.saplingMaterials.contains(saplingBlock.getType())) {
            if (countdown <= 0) {
                saplingBlock.setType(Material.AIR);
                SaplingListener.locationToSapling.remove(saplingBlock.getLocation());
                tree.growPhased(PluginConfig.getPhasePeriod());
                cancel();
            } else {
                if (isWart(saplingBlock.getType())) {
                    if (legacy) {
                        if (superLegacy) {
                            for (int i = 0; i < 5; i++) {
                                double offX = TreeMath.randomDouble(-0.5, 0.5);
                                double offY = TreeMath.randomDouble(-0.7, 0.7);
                                double offZ = TreeMath.randomDouble(-0.5, 0.5);
                                seed.getWorld().playEffect(seed.clone().add(offX, offY, offZ), Effect.valueOf("COLOURED_DUST"), 1);
                            }
                        } else {
                            for (int i = 0; i < 5; i++) {
                                double offX = TreeMath.randomDouble(-0.5, 0.5);
                                double offY = TreeMath.randomDouble(-0.7, 0.7);
                                double offZ = TreeMath.randomDouble(-0.5, 0.5);
                                seed.getWorld().spawnParticle(Particle.REDSTONE, seed.getX() + offX, seed.getY() + offY, seed.getZ() + offZ, 0, 255, 0, 0, 1);
                            }
                        }
                    } else {
                        seed.getWorld().spawnParticle(Particle.REDSTONE, seed, 7, 0.5, 0.7, 0.5, new Particle.DustOptions(Color.RED, 0.77f));
                    }

                } else {
                    if(superLegacy){
                        for (int i = 0; i < 5; i++) {
                            double offX = TreeMath.randomDouble(-0.5, 0.5);
                            double offY = TreeMath.randomDouble(-0.7, 0.7);
                            double offZ = TreeMath.randomDouble(-0.5, 0.5);
                            seed.getWorld().playEffect(seed.clone().add(offX, offY, offZ), Effect.valueOf("HAPPY_VILLAGER"), 1);
                        }
                    }else{
                        seed.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, seed, 7, 0.5, 0.7, 0.5);
                    }
                }

                countdown--;
            }
        } else {
            cancel();
        }
    }

    private static boolean legacy = Version.current.value <= 112;
    private static boolean superLegacy = Version.current.value <= 108;

    public static ImmutableSet<Material> saplingMaterials = buildSaplingSet();

    private static ImmutableSet<Material> buildSaplingSet() {
        return !legacy ?
                new ImmutableSet.Builder<Material>()
                        .add(XMaterial.OAK_SAPLING.parseMaterial())
                        .add(XMaterial.SPRUCE_SAPLING.parseMaterial())
                        .add(XMaterial.BIRCH_SAPLING.parseMaterial())
                        .add(XMaterial.JUNGLE_SAPLING.parseMaterial())
                        .add(XMaterial.ACACIA_SAPLING.parseMaterial())
                        .add(XMaterial.DARK_OAK_SAPLING.parseMaterial())
                        .add(XMaterial.NETHER_WART.parseMaterial())
                        .build() :
                new ImmutableSet.Builder<Material>()
                        .add(XMaterial.OAK_SAPLING.parseMaterial())
                        .add(Material.valueOf("NETHER_STALK"))
                        .add(Material.valueOf("NETHER_WARTS"))
                        .build();
    }

    public boolean isWart(Material material) {
        if (!legacy) {
            return material == XMaterial.NETHER_WART.parseMaterial();
        } else {
            return material == Material.valueOf("NETHER_STALK") || material == Material.valueOf("NETHER_WARTS");
        }
    }

}
