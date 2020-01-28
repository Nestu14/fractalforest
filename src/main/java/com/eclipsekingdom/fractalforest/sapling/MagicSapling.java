package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.sys.config.PluginConfig;
import com.eclipsekingdom.fractalforest.trees.ITree;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.trees.effect.IEffects;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class MagicSapling extends BukkitRunnable {

    private int countdown = 3;
    private Location seed;
    private Block saplingBlock;
    private ITree tree;
    private IEffects effectType;

    public MagicSapling(Player planter, Species species, Location seed) {
        this.seed = seed;
        this.tree = species.getIndividual(planter, seed);
        this.saplingBlock = seed.getBlock();
        this.effectType = species.getEffects();
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
                effectType.playSaplingParticles(seed);
                countdown--;
            }
        } else {
            cancel();
        }
    }

    private static boolean legacy = Version.current.value <= 112;

    public static Set<Material> saplingMaterials = buildSaplingSet();

    private static HashSet<Material> buildSaplingSet() {
        HashSet<Material> materials = new HashSet<>();
        if (legacy) {
            materials.add(XMaterial.OAK_SAPLING.parseMaterial());
            materials.add(XMaterial.OBSIDIAN.parseMaterial());
            materials.add(XMaterial.CHORUS_PLANT.parseMaterial());
            materials.add(Material.valueOf("NETHER_STALK"));
            materials.add(Material.valueOf("NETHER_WARTS"));
        } else {
            materials.add(XMaterial.OAK_SAPLING.parseMaterial());
            materials.add(XMaterial.SPRUCE_SAPLING.parseMaterial());
            materials.add(XMaterial.BIRCH_SAPLING.parseMaterial());
            materials.add(XMaterial.JUNGLE_SAPLING.parseMaterial());
            materials.add(XMaterial.ACACIA_SAPLING.parseMaterial());
            materials.add(XMaterial.DARK_OAK_SAPLING.parseMaterial());
            materials.add(XMaterial.NETHER_WART.parseMaterial());
            materials.add(XMaterial.OBSIDIAN.parseMaterial());
            materials.add(XMaterial.CHORUS_PLANT.parseMaterial());
        }
        return materials;
    }

}
