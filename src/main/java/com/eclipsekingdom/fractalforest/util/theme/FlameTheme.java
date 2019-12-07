package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import com.eclipsekingdom.fractalforest.util.theme.material.WeightedMaterialJumble;
import org.bukkit.Material;

public class FlameTheme extends Theme {

    public FlameTheme() {
        this.leaf = new MaterialSingleton(Material.MAGMA_BLOCK);
        this.thickBranch = new WeightedMaterialJumble().add(Material.RED_NETHER_BRICKS, 3).add(Material.NETHERRACK, 1);
        this.thinBranch = new MaterialSingleton(Material.RED_NETHER_BRICK_WALL);
        this.root = new WeightedMaterialJumble().add(Material.RED_NETHER_BRICKS, 3).add(Material.NETHERRACK, 1);
    }

}
