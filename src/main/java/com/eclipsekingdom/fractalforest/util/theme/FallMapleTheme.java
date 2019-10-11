package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

public class FallMapleTheme extends Theme{
    
    public FallMapleTheme(){
        this.leaf = new MaterialJumble()
                .add(Material.ORANGE_TERRACOTTA)
                .add(Material.ORANGE_CONCRETE)
                .add(Material.ORANGE_WOOL)
                .add(Material.YELLOW_TERRACOTTA)
                .add(Material.YELLOW_CONCRETE)
                .add(Material.YELLOW_WOOL)
                .add(Material.RED_TERRACOTTA)
                .add(Material.RED_CONCRETE)
                .add(Material.RED_WOOL);
                
        this.thickBranch = new MaterialSingleton(Material.OAK_WOOD);
        this.thinBranch = new MaterialSingleton(Material.SPRUCE_FENCE);
        this.root = new MaterialSingleton(Material.OAK_WOOD);
    }
}
