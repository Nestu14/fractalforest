package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

public class BirchTheme extends Theme {

    public BirchTheme() {
        this.leaf = new MaterialSingleton(Material.BIRCH_LEAVES);
        this.thickBranch = new MaterialSingleton(Material.BIRCH_WOOD);
        this.thinBranch = new MaterialSingleton(Material.BIRCH_FENCE);
        this.root = new MaterialSingleton(Material.BIRCH_WOOD);
    }
}
