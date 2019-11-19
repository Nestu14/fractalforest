package com.eclipsekingdom.fractalforest.gen.pop.util;

import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.HashSet;
import java.util.Set;

public enum TreeBiome {

    PLAINS(Material.GRASS_BLOCK, new Biome[]{Biome.PLAINS}),
    SUNFLOWER_PLAINS(Material.SUNFLOWER, new Biome[]{Biome.SUNFLOWER_PLAINS}),
    DESERT(Material.SAND, new Biome[]{Biome.DESERT, Biome.DESERT_HILLS}),
    MOUNTAINS(Material.STONE, new Biome[]{Biome.MOUNTAINS, Biome.MOUNTAIN_EDGE, Biome.MODIFIED_GRAVELLY_MOUNTAINS, Biome.GRAVELLY_MOUNTAINS}),
    WOODED_MOUNTAINS(Material.OAK_WOOD, new Biome[]{Biome.WOODED_MOUNTAINS}),
    TAIGA_MOUNTAIN(Material.SPRUCE_WOOD, new Biome[]{Biome.TAIGA_MOUNTAINS}),
    SNOWY_MOUNTAIN(Material.SNOW_BLOCK, new Biome[]{Biome.SNOWY_MOUNTAINS}),
    TAIGA(Material.SPRUCE_WOOD, new Biome[]{Biome.TAIGA, Biome.TAIGA_HILLS}),
    SNOWY_TAIGA(Material.SNOW_BLOCK, new Biome[]{Biome.SNOWY_TAIGA, Biome.SNOWY_TAIGA_HILLS}),
    GIANT_TAIGA(Material.SPRUCE_WOOD, new Biome[]{Biome.GIANT_SPRUCE_TAIGA_HILLS, Biome.GIANT_SPRUCE_TAIGA, Biome.GIANT_TREE_TAIGA_HILLS, Biome.GIANT_TREE_TAIGA}),
    SWAMP(Material.LILY_PAD, new Biome[]{Biome.SWAMP_HILLS, Biome.SWAMP}),
    SNOWY_TUNDRA(Material.SNOW_BLOCK, new Biome[]{Biome.SNOWY_TUNDRA}),
    BEACH(Material.SAND, new Biome[]{Biome.BEACH}),
    FOREST(Material.OAK_WOOD, new Biome[]{Biome.FOREST, Biome.WOODED_HILLS}),
    JUNGLE(Material.JUNGLE_WOOD, new Biome[]{Biome.JUNGLE, Biome.JUNGLE_HILLS, Biome.JUNGLE_EDGE.MODIFIED_JUNGLE, Biome.MODIFIED_JUNGLE_EDGE}),
    BAMBOO_JUNGLE(Material.BAMBOO, new Biome[]{Biome.BAMBOO_JUNGLE, Biome.BAMBOO_JUNGLE_HILLS}),
    BIRCH_FOREST(Material.BIRCH_WOOD, new Biome[]{Biome.BIRCH_FOREST, Biome.BIRCH_FOREST_HILLS, Biome.TALL_BIRCH_FOREST, Biome.TALL_BIRCH_HILLS}),
    DARK_FOREST(Material.DARK_OAK_WOOD, new Biome[]{Biome.DARK_FOREST, Biome.DARK_FOREST_HILLS}),
    SAVANA(Material.ACACIA_WOOD, new Biome[]{Biome.SAVANNA_PLATEAU, Biome.SAVANNA, Biome.SHATTERED_SAVANNA_PLATEAU, Biome.SHATTERED_SAVANNA}),
    BADLANDS(Material.RED_TERRACOTTA, new Biome[]{Biome.BADLANDS_PLATEAU, Biome.BADLANDS, Biome.MODIFIED_BADLANDS_PLATEAU, Biome.MODIFIED_WOODED_BADLANDS_PLATEAU, Biome.WOODED_BADLANDS_PLATEAU, Biome.ERODED_BADLANDS}),
    FLOWER_FOREST(Material.POPPY, new Biome[]{Biome.FLOWER_FOREST}),
    NONE(Material.BARRIER, new Biome[]{}),
    ;

    private Set<Biome> biomes = new HashSet<>();
    private Material material;

    TreeBiome(Material material, Biome[] biomes) {
        for (Biome b : biomes) {
            this.biomes.add(b);
        }
        this.material = material;
    }

    public boolean contains(Biome biome) {
        return biomes.contains(biome);
    }

    public Material getMaterial() {
        return material;
    }

    public static TreeBiome from(Biome biome) {
        for (TreeBiome treeBiome : values()) {
            if (treeBiome.biomes.contains(biome)) {
                return treeBiome;
            }
        }
        return NONE;
    }

}