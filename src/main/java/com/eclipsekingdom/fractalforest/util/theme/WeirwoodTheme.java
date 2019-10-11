package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

public class WeirwoodTheme extends Theme {

    public WeirwoodTheme() {
        this.leaf = new MaterialJumble().add(Material.RED_TERRACOTTA).add(Material.RED_CONCRETE).add(Material.RED_WOOL);
        this.thickBranch = new MaterialSingleton(Material.BIRCH_WOOD);
        this.thinBranch = new MaterialSingleton(Material.BIRCH_FENCE);
        this.root = new MaterialSingleton(Material.BIRCH_WOOD);
    }


}
