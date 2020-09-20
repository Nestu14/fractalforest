package com.eclipsekingdom.fractalforest.worldgen.pop.util;

import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.util.X.XBiome;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.eclipsekingdom.fractalforest.util.X.XTree;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum TreeBiome {

    PLAINS(0, new XBiome[]{XBiome.PLAINS, XBiome.SUNFLOWER_PLAINS}, XMaterial.GRASS_BLOCK),
    DESERT(0, new XBiome[]{XBiome.DESERT, XBiome.DESERT_HILLS}, XMaterial.SAND),
    MOUNTAINS(0, new XBiome[]{XBiome.MOUNTAINS, XBiome.MOUNTAIN_EDGE, XBiome.MODIFIED_GRAVELLY_MOUNTAINS, XBiome.GRAVELLY_MOUNTAINS}, XMaterial.STONE),
    WOODED_MOUNTAINS(0, new XBiome[]{XBiome.WOODED_MOUNTAINS}, XTree.OAK),
    TAIGA_MOUNTAIN(0, new XBiome[]{XBiome.TAIGA_MOUNTAINS}, XTree.SPRUCE),
    SNOWY_MOUNTAIN(0, new XBiome[]{XBiome.SNOWY_MOUNTAINS}, XMaterial.SNOW_BLOCK),
    TAIGA(0, new XBiome[]{XBiome.TAIGA, XBiome.TAIGA_HILLS}, XTree.SPRUCE),
    SNOWY_TAIGA(0, new XBiome[]{XBiome.SNOWY_TAIGA, XBiome.SNOWY_TAIGA_HILLS}, XMaterial.SNOW_BLOCK),
    GIANT_TAIGA(0, new XBiome[]{XBiome.GIANT_SPRUCE_TAIGA_HILLS, XBiome.GIANT_SPRUCE_TAIGA, XBiome.GIANT_TREE_TAIGA_HILLS, XBiome.GIANT_TREE_TAIGA}, XTree.SPRUCE),
    SWAMP(0, new XBiome[]{XBiome.SWAMP_HILLS, XBiome.SWAMP}, XMaterial.LILY_PAD),
    SNOWY_TUNDRA(0, new XBiome[]{XBiome.SNOWY_TUNDRA}, XMaterial.SNOW_BLOCK),
    BEACH(0, new XBiome[]{XBiome.BEACH}, XMaterial.SAND),
    FOREST(0, new XBiome[]{XBiome.FOREST, XBiome.WOODED_HILLS}, XTree.OAK),
    JUNGLE(0, new XBiome[]{XBiome.JUNGLE, XBiome.JUNGLE_HILLS, XBiome.JUNGLE_EDGE.MODIFIED_JUNGLE, XBiome.MODIFIED_JUNGLE_EDGE}, XTree.JUNGLE),
    BAMBOO_JUNGLE(114, new XBiome[]{XBiome.BAMBOO_JUNGLE, XBiome.BAMBOO_JUNGLE_HILLS}, XMaterial.BAMBOO),
    BIRCH_FOREST(0, new XBiome[]{XBiome.BIRCH_FOREST, XBiome.BIRCH_FOREST_HILLS, XBiome.TALL_BIRCH_FOREST, XBiome.TALL_BIRCH_HILLS}, XTree.BIRCH),
    DARK_FOREST(0, new XBiome[]{XBiome.DARK_FOREST, XBiome.DARK_FOREST_HILLS}, XTree.DARK_OAK),
    SAVANNA(0, new XBiome[]{XBiome.SAVANNA_PLATEAU, XBiome.SAVANNA, XBiome.SHATTERED_SAVANNA_PLATEAU, XBiome.SHATTERED_SAVANNA}, XTree.ACACIA),
    BADLANDS(0, new XBiome[]{XBiome.BADLANDS_PLATEAU, XBiome.BADLANDS, XBiome.MODIFIED_BADLANDS_PLATEAU, XBiome.MODIFIED_WOODED_BADLANDS_PLATEAU, XBiome.WOODED_BADLANDS_PLATEAU, XBiome.ERODED_BADLANDS}, XMaterial.TERRACOTTA),
    FLOWER_FOREST(0, new XBiome[]{XBiome.FLOWER_FOREST}, XMaterial.POPPY),
    NETHER_WASTES(0, new XBiome[]{XBiome.NETHER_WASTES}, XMaterial.NETHERRACK),
    SOUL_SAND_VALLEY(116, new XBiome[]{XBiome.SOUL_SAND_VALLEY}, XMaterial.SOUL_SAND),
    CRIMSON_FOREST(116, new XBiome[]{XBiome.CRIMSON_FOREST}, XMaterial.CRIMSON_NYLIUM),
    WARPED_FOREST(116, new XBiome[]{XBiome.WARPED_FOREST}, XMaterial.WARPED_NYLIUM),
    BASALT_DELTAS(116, new XBiome[]{XBiome.BASALT_DELTAS}, XMaterial.BASALT),
    END(0, new XBiome[]{XBiome.END_BARRENS, XBiome.END_HIGHLANDS, XBiome.END_MIDLANDS, XBiome.SMALL_END_ISLANDS, XBiome.THE_END}, XMaterial.END_STONE),
    NONE(0, new XBiome[]{}, XMaterial.BARRIER),
    ;

    private Set<Biome> biomes = new HashSet<>();
    private ItemStack itemStack;
    private int version;

    TreeBiome(int version, XBiome[] biomes, XMaterial xMaterial) {
        this.version = version;
        Material material = xMaterial.isSupported() ? xMaterial.parseMaterial() : XMaterial.BARRIER.parseMaterial();
        this.itemStack = makeBiomeStack(this, new ItemStack(material));
        for (XBiome b : biomes) {
            this.biomes.add(b.parseBiome());
        }
    }

    TreeBiome(int version, XBiome[] biomes, XTree xTree) {
        this.version = version;
        this.itemStack = makeBiomeStack(this, xTree.getItemStack());
        for (XBiome b : biomes) {
            this.biomes.add(b.parseBiome());
        }
    }

    public boolean contains(Biome biome) {
        return biomes.contains(biome);
    }

    public static TreeBiome from(Biome biome) {
        for (TreeBiome treeBiome : values()) {
            if (treeBiome.biomes.contains(biome)) {
                return treeBiome;
            }
        }
        return NONE;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    private static ItemStack makeBiomeStack(TreeBiome treeBiome, ItemStack base) {
        ItemMeta meta = base.getItemMeta();
        meta.setDisplayName(treeBiome.toString());
        base.setItemMeta(meta);
        return base;
    }

    private static List<TreeBiome> supportedBiomes = buildSupportedBiomes();

    private static List<TreeBiome> buildSupportedBiomes() {
        List<TreeBiome> treeBiomes = new ArrayList<>();
        for (TreeBiome biome : values()) {
            if (Version.current.value >= biome.version) {
                treeBiomes.add(biome);
            }
        }
        return treeBiomes;
    }

    public static TreeBiome[] getSupportedBiomes() {
        return supportedBiomes.toArray(new TreeBiome[]{});
    }

}