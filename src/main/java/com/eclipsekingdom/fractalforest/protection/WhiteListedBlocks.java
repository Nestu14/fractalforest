package com.eclipsekingdom.fractalforest.protection;

import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class WhiteListedBlocks {

    public static Set<Material> trunkWhitelist = makeTrunkWhiteList();

    public static Set<Material> branchWhitelist = makeBranchWhiteList();


    public static Set<Material> makeTrunkWhiteList() {
        Set<Material> trunkWhitelist = new HashSet<>();
        trunkWhitelist.add(Material.DIRT);
        trunkWhitelist.add(XMaterial.GRASS_BLOCK.parseMaterial());
        trunkWhitelist.add(XMaterial.COARSE_DIRT.parseMaterial());
        trunkWhitelist.add(XMaterial.SOUL_SAND.parseMaterial());
        trunkWhitelist.add(XMaterial.SAND.parseMaterial());
        trunkWhitelist.add(XMaterial.GRAVEL.parseMaterial());
        trunkWhitelist.add(XMaterial.GRASS_PATH.parseMaterial());
        trunkWhitelist.add(XMaterial.OAK_LEAVES.parseMaterial());
        trunkWhitelist.add(XMaterial.SPRUCE_LEAVES.parseMaterial());
        trunkWhitelist.add(XMaterial.BIRCH_LEAVES.parseMaterial());
        trunkWhitelist.add(XMaterial.JUNGLE_LEAVES.parseMaterial());
        trunkWhitelist.add(XMaterial.ACACIA_LEAVES.parseMaterial());
        trunkWhitelist.add(XMaterial.DARK_OAK_LEAVES.parseMaterial());
        return trunkWhitelist;
    }

    public static Set<Material> makeBranchWhiteList() {
        Set<Material> leafWhiteList = new HashSet<>();
        leafWhiteList.add(XMaterial.OAK_LEAVES.parseMaterial());
        leafWhiteList.add(XMaterial.SPRUCE_LEAVES.parseMaterial());
        leafWhiteList.add(XMaterial.BIRCH_LEAVES.parseMaterial());
        leafWhiteList.add(XMaterial.JUNGLE_LEAVES.parseMaterial());
        leafWhiteList.add(XMaterial.ACACIA_LEAVES.parseMaterial());
        leafWhiteList.add(XMaterial.DARK_OAK_LEAVES.parseMaterial());
        return leafWhiteList;
    }


    public static Set<Material> rootWhiteList = makeRootWhiteList();

    public static Set<Material> makeRootWhiteList() {
        Set<Material> rootWhiteList = new HashSet<>();
        rootWhiteList.add(Material.STONE);
        rootWhiteList.add(XMaterial.ANDESITE.parseMaterial());
        rootWhiteList.add(XMaterial.DIORITE.parseMaterial());
        rootWhiteList.add(XMaterial.GRANITE.parseMaterial());
        return rootWhiteList;
    }

}
