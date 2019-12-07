package com.eclipsekingdom.fractalforest.util.theme.material;

import com.eclipsekingdom.fractalforest.util.math.random.WeightedChoice;
import com.eclipsekingdom.fractalforest.util.math.random.WeightedRandomizer;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class WeightedMaterialJumble implements IMaterialFactory {

    private WeightedRandomizer<Material> materials;
    private Set<Material> domain = new HashSet<>();

    public WeightedMaterialJumble(){
        materials = new WeightedRandomizer<>();
    }

    public WeightedMaterialJumble add(Material material, int weight) {
        domain.add(material);
        materials.add(new WeightedChoice<>(material, weight));
        return this;
    }

    @Override
    public Material select(Random random) {
        return materials.get(random);
    }

    @Override
    public Set<Material> domain() {
        return domain;
    }
}
