package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

public class FallBirchTheme extends Theme {

    public FallBirchTheme() {
        this.leaf = new MaterialJumble().add(Material.YELLOW_TERRACOTTA).add(Material.YELLOW_CONCRETE).add(Material.YELLOW_WOOL);
        this.thickBranch = new MaterialSingleton(Material.BIRCH_WOOD);
        this.thinBranch = new MaterialSingleton(Material.BIRCH_FENCE);
        this.root = new MaterialSingleton(Material.BIRCH_WOOD);
    }

}
