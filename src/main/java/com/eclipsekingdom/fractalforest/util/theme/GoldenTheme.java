package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import com.eclipsekingdom.fractalforest.util.theme.material.WeightedMaterialJumble;
import org.bukkit.Material;

public class GoldenTheme extends Theme {

    public GoldenTheme() {
        this.leaf = new WeightedMaterialJumble().add(Material.OAK_LEAVES, 25).add(Material.GLOWSTONE, 1);
        this.thickBranch = new MaterialSingleton(Material.OAK_WOOD);
        this.thinBranch = new MaterialSingleton(Material.SPRUCE_FENCE);
        this.root = new MaterialSingleton(Material.OAK_WOOD);
    }
}
