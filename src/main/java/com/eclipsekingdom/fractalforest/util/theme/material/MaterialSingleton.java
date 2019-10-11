package com.eclipsekingdom.fractalforest.util.theme.material;

import org.bukkit.Material;

import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class MaterialSingleton implements IMaterialFactory {

    private Material material;

    public MaterialSingleton(Material material){
        this.material = material;
    }

    @Override
    public Material select(Random random) {
        return material;
    }

    @Override
    public Set<Material> domain() {
        return Collections.singleton(material);
    }

}
