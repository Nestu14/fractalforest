package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

public class FallElmTheme extends Theme{

    public FallElmTheme() {
        this.leaf = new MaterialJumble()
                .add(Material.BROWN_TERRACOTTA)
                .add(Material.BROWN_CONCRETE)
                .add(Material.BROWN_WOOL)
                .add(Material.YELLOW_TERRACOTTA)
                .add(Material.YELLOW_CONCRETE)
                .add(Material.YELLOW_WOOL);
        this.thickBranch = new MaterialSingleton(Material.OAK_WOOD);
        this.thinBranch = new MaterialSingleton(Material.SPRUCE_FENCE);
        this.root = new MaterialSingleton(Material.OAK_WOOD);
    }

}
