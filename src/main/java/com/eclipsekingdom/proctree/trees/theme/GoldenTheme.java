package com.eclipsekingdom.proctree.trees.theme;

import com.eclipsekingdom.proctree.trees.material.MaterialSingleton;
import com.eclipsekingdom.proctree.trees.material.WeightedMaterialJumble;
import org.bukkit.Material;

public class GoldenTheme extends ThemeBase {
    public GoldenTheme() {
        this.leaf = new WeightedMaterialJumble().add(Material.OAK_LEAVES,25).add(Material.GLOWSTONE,1);
        this.thickBranch = new MaterialSingleton(Material.OAK_LOG);
        this.thinBranch = new MaterialSingleton(Material.SPRUCE_FENCE);
        this.root = new MaterialSingleton(Material.OAK_LOG);
    }

}
