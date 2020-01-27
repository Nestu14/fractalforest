package com.eclipsekingdom.fractalforest.util;

import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreeSpawner;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import java.util.*;

public class TreeUtil {

    private static int versionValue = Version.current.value;

    public static void callEvent(Event event) {
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public static LinkedHashMap<TreeBiome, List<TreeSpawner>> clone(LinkedHashMap<TreeBiome, List<TreeSpawner>> biomeToTreeSpawner) {
        LinkedHashMap<TreeBiome, List<TreeSpawner>> clone = new LinkedHashMap<>();
        for (Map.Entry<TreeBiome, List<TreeSpawner>> entry : biomeToTreeSpawner.entrySet()) {
            List<TreeSpawner> clonedSpawners = new ArrayList<>();
            for (TreeSpawner treeSpawner : entry.getValue()) {
                clonedSpawners.add(treeSpawner.clone());
            }
            clone.put(entry.getKey(), clonedSpawners);
        }
        return clone;
    }

    public static void makeLeafPermanent(Block block) {
        if (versionValue >= 113) {
            LeafDataUtil.makePermanent(block);
        }
    }


    public static boolean isPassable(Material material) {
        return passableMaterials.contains(material);
    }

    public static Set<Material> passableMaterials = createPassableSet();

    public static HashSet<Material> createPassableSet() {
        HashSet<Material> passableSet = new HashSet<>();
        passableSet.add(XMaterial.AIR.parseMaterial());
        passableSet.add(XMaterial.GRASS.parseMaterial());
        passableSet.add(XMaterial.OAK_SAPLING.parseMaterial());
        passableSet.add(XMaterial.SPRUCE_SAPLING.parseMaterial());
        passableSet.add(XMaterial.BIRCH_SAPLING.parseMaterial());
        passableSet.add(XMaterial.JUNGLE_SAPLING.parseMaterial());
        passableSet.add(XMaterial.ACACIA_SAPLING.parseMaterial());
        passableSet.add(XMaterial.DARK_OAK_SAPLING.parseMaterial());
        passableSet.add(XMaterial.GRASS.parseMaterial());
        passableSet.add(XMaterial.DANDELION.parseMaterial());
        passableSet.add(XMaterial.POPPY.parseMaterial());
        passableSet.add(XMaterial.BLUE_ORCHID.parseMaterial());
        passableSet.add(XMaterial.ALLIUM.parseMaterial());
        passableSet.add(XMaterial.AZURE_BLUET.parseMaterial());
        passableSet.add(XMaterial.RED_TULIP.parseMaterial());
        passableSet.add(XMaterial.ORANGE_TULIP.parseMaterial());
        passableSet.add(XMaterial.WHITE_TULIP.parseMaterial());
        passableSet.add(XMaterial.PINK_TULIP.parseMaterial());
        passableSet.add(XMaterial.OXEYE_DAISY.parseMaterial());
        passableSet.add(XMaterial.BROWN_MUSHROOM.parseMaterial());
        passableSet.add(XMaterial.RED_MUSHROOM.parseMaterial());
        passableSet.add(XMaterial.COBWEB.parseMaterial());
        passableSet.add(XMaterial.FERN.parseMaterial());
        passableSet.add(XMaterial.DEAD_BUSH.parseMaterial());
        passableSet.add(XMaterial.VINE.parseMaterial());
        passableSet.add(XMaterial.LILY_PAD.parseMaterial());
        passableSet.add(XMaterial.LILAC.parseMaterial());
        passableSet.add(XMaterial.TALL_GRASS.parseMaterial());
        passableSet.add(XMaterial.LARGE_FERN.parseMaterial());
        passableSet.add(XMaterial.ROSE_BUSH.parseMaterial());
        passableSet.add(XMaterial.PEONY.parseMaterial());
        passableSet.add(XMaterial.SWEET_BERRY_BUSH.parseMaterial());
        passableSet.add(XMaterial.SUGAR_CANE.parseMaterial());
        passableSet.add(XMaterial.SNOW.parseMaterial());

        return passableSet;
    }

}
