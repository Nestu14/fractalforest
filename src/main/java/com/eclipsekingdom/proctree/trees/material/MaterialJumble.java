package com.eclipsekingdom.proctree.trees.material;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialJumble implements IMaterialFactory {

    private List<Material> materials = new ArrayList<>();

    public MaterialJumble add(Material material){
        materials.add(material);
        return this;
    }

    @Override
    public Material select(Random random) {
        return materials.get(random.nextInt(materials.size()));
    }
}
