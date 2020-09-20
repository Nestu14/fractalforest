package com.eclipsekingdom.fractalforest.util.theme.material;

import com.eclipsekingdom.fractalforest.util.X.FMaterial;
import com.eclipsekingdom.fractalforest.util.math.random.WeightedChoice;
import com.eclipsekingdom.fractalforest.util.math.random.WeightedRandomizer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class WeightedMaterialJumble implements IMaterialFactory {

    private WeightedRandomizer<FMaterial> materials;
    private Set<FMaterial> domain = new HashSet<>();

    public WeightedMaterialJumble(){
        materials = new WeightedRandomizer<>();
    }

    public WeightedMaterialJumble add(FMaterial material, int weight) {
        domain.add(material);
        materials.add(new WeightedChoice<>(material, weight));
        return this;
    }

    @Override
    public FMaterial select(Random random) {
        return materials.get(random);
    }

    @Override
    public Set<FMaterial> domain() {
        return domain;
    }
}
