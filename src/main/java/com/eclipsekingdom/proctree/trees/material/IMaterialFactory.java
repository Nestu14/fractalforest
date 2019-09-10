package com.eclipsekingdom.proctree.trees.material;

import org.bukkit.Material;

import java.util.Random;

public interface IMaterialFactory {
    Material select(Random random);
}
