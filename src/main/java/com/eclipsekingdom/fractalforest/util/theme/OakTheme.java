package com.eclipsekingdom.fractalforest.util.theme;


import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

public class OakTheme extends Theme {

    public OakTheme() {
        this.leaf = new MaterialSingleton(Material.OAK_LEAVES);
        this.thickBranch = new MaterialSingleton(Material.OAK_WOOD);
        this.thinBranch = new MaterialSingleton(Material.SPRUCE_FENCE);
        this.root = new MaterialSingleton(Material.OAK_WOOD);

    }

}
