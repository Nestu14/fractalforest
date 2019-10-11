package com.eclipsekingdom.fractalforest.protection;

import com.google.common.collect.ImmutableSet;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class WhiteListedBlocks {

    private static Set<Material> blankList = new HashSet<>();

    public static ImmutableSet<Material> trunkWhitelist = new ImmutableSet.Builder<Material>()
            .add(Material.DIRT)
            .add(Material.GRASS_BLOCK)
            .add(Material.COARSE_DIRT)
            .add(Material.SOUL_SAND)
            .add(Material.SAND)
            .add(Material.GRAVEL)
            .add(Material.GRASS_PATH).build();


    public static ImmutableSet<Material> rootWhiteList = new ImmutableSet.Builder<Material>()
            .addAll(trunkWhitelist)
            .add(Material.STONE)
            .add(Material.ANDESITE)
            .add(Material.DIORITE)
            .add(Material.GRANITE).build();


    //leaves -- empty
    //outerleaves -- empty, tree material
    //branches -- empty, tree material
    //trunk -- empty, passable, dirt block, tree material
    //roots -- empty, passable, dirt block, tree material, stone blocks.

}
