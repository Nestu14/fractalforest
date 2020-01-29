package com.eclipsekingdom.fractalforest.trees.habitat;

import org.bukkit.Location;
import org.bukkit.Material;

public interface IHabitat {

    boolean canPlantAt(Location location);

    boolean isSoil(Material material);

}
