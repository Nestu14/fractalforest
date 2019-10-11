package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

public class FloweringHawthornTheme extends Theme{

    public FloweringHawthornTheme(){
        this.leaf = new MaterialJumble()
                .add(Material.PINK_TERRACOTTA)
                .add(Material.PINK_CONCRETE)
                .add(Material.PINK_WOOL);
        this.thickBranch = new MaterialSingleton(Material.OAK_WOOD);
        this.thinBranch = new MaterialSingleton(Material.SPRUCE_FENCE);
        this.root = new MaterialSingleton(Material.OAK_WOOD);
    }

}
