package com.eclipsekingdom.fractalforest.util.theme.material;

import org.bukkit.Material;

import java.util.Random;
import java.util.Set;

public interface IMaterialFactory {
    Material select(Random random);
    Set<Material> domain();
}
