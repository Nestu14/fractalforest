package com.eclipsekingdom.proctree.trees.material;

import org.bukkit.Material;

import java.util.Random;

public class MaterialSingleton implements IMaterialFactory {

    private Material material;

    public MaterialSingleton(Material material){
        this.material = material;
    }

    @Override
    public Material select(Random random) {
        return material;
    }

}
