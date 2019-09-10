package com.eclipsekingdom.proctree.trees.material;

import com.eclipsekingdom.proctree.util.random.WeightedChoice;
import com.eclipsekingdom.proctree.util.random.WeightedRandomizer;
import org.bukkit.Material;

import java.util.Random;

public class WeightedMaterialJumble implements IMaterialFactory {

    private WeightedRandomizer<Material> materials;

    public WeightedMaterialJumble(){
        materials = new WeightedRandomizer<>();
    }

    public WeightedMaterialJumble add(Material material, int weight) {
        materials.add(new WeightedChoice<>(material, weight));
        return this;
    }

    @Override
    public Material select(Random random) {
        return materials.get(random);
    }
}
