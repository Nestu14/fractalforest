package com.eclipsekingdom.fractalforest.worldgen.pop.util;

import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.util.X.XBiome;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.eclipsekingdom.fractalforest.util.X.XTree;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;

public enum TreeBiome {

    PLAINS(new Biome[]{Biome.PLAINS, XBiome.SUNFLOWER_PLAINS.parseBiome()}),
    DESERT(new Biome[]{Biome.DESERT, Biome.DESERT_HILLS}),
    MOUNTAINS(new Biome[]{XBiome.MOUNTAINS.parseBiome(), XBiome.MOUNTAIN_EDGE.parseBiome(), XBiome.MODIFIED_GRAVELLY_MOUNTAINS.parseBiome(), XBiome.GRAVELLY_MOUNTAINS.parseBiome()}),
    WOODED_MOUNTAINS(new Biome[]{XBiome.WOODED_MOUNTAINS.parseBiome()}),
    TAIGA_MOUNTAIN(new Biome[]{XBiome.TAIGA_MOUNTAINS.parseBiome()}),
    SNOWY_MOUNTAIN(new Biome[]{XBiome.SNOWY_MOUNTAINS.parseBiome()}),
    TAIGA(new Biome[]{Biome.TAIGA, Biome.TAIGA_HILLS}),
    SNOWY_TAIGA(new Biome[]{XBiome.SNOWY_TAIGA.parseBiome(), XBiome.SNOWY_TAIGA_HILLS.parseBiome()}),
    GIANT_TAIGA(new Biome[]{XBiome.GIANT_SPRUCE_TAIGA_HILLS.parseBiome(), XBiome.GIANT_SPRUCE_TAIGA.parseBiome(), XBiome.GIANT_TREE_TAIGA_HILLS.parseBiome(), XBiome.GIANT_TREE_TAIGA.parseBiome()}),
    SWAMP(new Biome[]{XBiome.SWAMP_HILLS.parseBiome(), XBiome.SWAMP.parseBiome()}),
    SNOWY_TUNDRA(new Biome[]{XBiome.SNOWY_TUNDRA.parseBiome()}),
    BEACH(new Biome[]{XBiome.BEACH.parseBiome()}),
    FOREST(new Biome[]{Biome.FOREST, XBiome.WOODED_HILLS.parseBiome()}),
    JUNGLE(new Biome[]{Biome.JUNGLE, Biome.JUNGLE_HILLS, XBiome.JUNGLE_EDGE.MODIFIED_JUNGLE.parseBiome(), XBiome.MODIFIED_JUNGLE_EDGE.parseBiome()}),
    BAMBOO_JUNGLE(new Biome[]{XBiome.BAMBOO_JUNGLE.parseBiome(), XBiome.BAMBOO_JUNGLE_HILLS.parseBiome()}),
    BIRCH_FOREST(new Biome[]{Biome.BIRCH_FOREST, Biome.BIRCH_FOREST_HILLS, XBiome.TALL_BIRCH_FOREST.parseBiome(), XBiome.TALL_BIRCH_HILLS.parseBiome()}),
    DARK_FOREST(new Biome[]{XBiome.DARK_FOREST.parseBiome(), XBiome.DARK_FOREST_HILLS.parseBiome()}),
    SAVANNA(new Biome[]{XBiome.SAVANNA_PLATEAU.parseBiome(), Biome.SAVANNA, XBiome.SHATTERED_SAVANNA_PLATEAU.parseBiome(), XBiome.SHATTERED_SAVANNA.parseBiome()}),
    BADLANDS(new Biome[]{XBiome.BADLANDS_PLATEAU.parseBiome(), XBiome.BADLANDS.parseBiome(), XBiome.MODIFIED_BADLANDS_PLATEAU.parseBiome(), XBiome.MODIFIED_WOODED_BADLANDS_PLATEAU.parseBiome(), XBiome.WOODED_BADLANDS_PLATEAU.parseBiome(), XBiome.ERODED_BADLANDS.parseBiome()}),
    FLOWER_FOREST(new Biome[]{XBiome.FLOWER_FOREST.parseBiome()}),
    NETHER(new Biome[]{XBiome.NETHER.parseBiome()}),
    END(new Biome[]{XBiome.END_BARRENS.parseBiome(), XBiome.END_HIGHLANDS.parseBiome(), XBiome.END_MIDLANDS.parseBiome(), XBiome.SMALL_END_ISLANDS.parseBiome(), XBiome.THE_END.parseBiome()}),
    NONE(new Biome[]{}),
    ;

    private Set<Biome> biomes = new HashSet<>();
    private ItemStack itemStack;

    TreeBiome(Biome[] biomes) {
        for (Biome b : biomes) {
            this.biomes.add(b);
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

    public static void init() {
        PLAINS.itemStack = makeBiomeStack(PLAINS, XMaterial.GRASS_BLOCK.parseMaterial());
        DESERT.itemStack = makeBiomeStack(DESERT, Material.SAND);
        MOUNTAINS.itemStack = makeBiomeStack(MOUNTAINS, Material.STONE);
        WOODED_MOUNTAINS.itemStack = makeBiomeStack(WOODED_MOUNTAINS, XTree.OAK);
        TAIGA_MOUNTAIN.itemStack = makeBiomeStack(TAIGA_MOUNTAIN, XTree.SPRUCE);
        SNOWY_MOUNTAIN.itemStack = makeBiomeStack(SNOWY_MOUNTAIN, Material.SNOW_BLOCK);
        TAIGA.itemStack = makeBiomeStack(TAIGA, XTree.SPRUCE);
        SNOWY_TAIGA.itemStack = makeBiomeStack(SNOWY_TAIGA, Material.SNOW_BLOCK);
        GIANT_TAIGA.itemStack = makeBiomeStack(GIANT_TAIGA, XTree.SPRUCE);
        SWAMP.itemStack = makeBiomeStack(SWAMP, XMaterial.LILY_PAD.parseMaterial());
        SNOWY_TUNDRA.itemStack = makeBiomeStack(SNOWY_TUNDRA, Material.SNOW_BLOCK);
        BEACH.itemStack = makeBiomeStack(BEACH, Material.SAND);
        FOREST.itemStack = makeBiomeStack(FOREST, XTree.OAK);
        JUNGLE.itemStack = makeBiomeStack(JUNGLE, XTree.JUNGLE);
        BAMBOO_JUNGLE.itemStack = makeBiomeStack(BAMBOO_JUNGLE, Version.current.value >= 114 ? Material.BAMBOO : Material.BARRIER);
        BIRCH_FOREST.itemStack = makeBiomeStack(BIRCH_FOREST, XTree.BIRCH);
        DARK_FOREST.itemStack = makeBiomeStack(DARK_FOREST, XTree.DARK_OAK);
        SAVANNA.itemStack = makeBiomeStack(SAVANNA, XTree.ACACIA);
        BADLANDS.itemStack = makeBiomeStack(BADLANDS, XMaterial.TERRACOTTA.parseItem());
        FLOWER_FOREST.itemStack = makeBiomeStack(FLOWER_FOREST, XMaterial.POPPY.parseMaterial());
        NETHER.itemStack = makeBiomeStack(NETHER, Material.NETHERRACK);
        END.itemStack = makeBiomeStack(END, XMaterial.END_STONE.parseMaterial());
        NONE.itemStack = makeBiomeStack(NONE, Material.BARRIER);
    }


    public static ItemStack makeBiomeStack(TreeBiome treeBiome, Material base) {
        return makeBiomeStack(treeBiome, new ItemStack(base));
    }

    public static ItemStack makeBiomeStack(TreeBiome treeBiome, XTree xTree) {
        ItemStack itemStack = Version.current.value >= 113 ? new ItemStack(xTree.getMaterial()) : new ItemStack(xTree.getMaterial(), 1, xTree.getaByte());
        return makeBiomeStack(treeBiome, itemStack);
    }

    public static ItemStack makeBiomeStack(TreeBiome treeBiome, ItemStack base) {
        ItemMeta meta = base.getItemMeta();
        meta.setDisplayName(treeBiome.toString());
        base.setItemMeta(meta);
        return base;
    }


}