package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

public class FallOakTheme extends Theme {

    public FallOakTheme() {
        this.leaf = new MaterialJumble()
                .add(Material.BROWN_TERRACOTTA)
                .add(Material.BROWN_CONCRETE)
                .add(Material.BROWN_WOOL)
                .add(Material.RED_TERRACOTTA)
                .add(Material.RED_CONCRETE)
                .add(Material.RED_WOOL);
        this.thickBranch = new MaterialSingleton(Material.OAK_WOOD);
        this.thinBranch = new MaterialSingleton(Material.SPRUCE_FENCE);
        this.root = new MaterialSingleton(Material.OAK_WOOD);
    }

}
