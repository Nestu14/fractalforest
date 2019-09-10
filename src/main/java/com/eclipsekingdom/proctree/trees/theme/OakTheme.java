package com.eclipsekingdom.proctree.trees.theme;


import com.eclipsekingdom.proctree.trees.material.MaterialSingleton;
import org.bukkit.Material;

public class OakTheme extends ThemeBase {

    public OakTheme() {
        this.leaf = new MaterialSingleton(Material.OAK_LEAVES);
        this.thickBranch = new MaterialSingleton(Material.OAK_LOG);
        this.thinBranch = new MaterialSingleton(Material.SPRUCE_FENCE);
        this.root = new MaterialSingleton(Material.OAK_LOG);
    }

}
