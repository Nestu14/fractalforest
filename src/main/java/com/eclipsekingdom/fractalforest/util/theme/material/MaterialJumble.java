package com.eclipsekingdom.fractalforest.util.theme.material;

import com.eclipsekingdom.fractalforest.util.X.FMaterial;

import java.util.*;

public class MaterialJumble implements IMaterialFactory {

    private List<FMaterial> materials = new ArrayList<>();
    private Set<FMaterial> domain = new HashSet<>();

    public MaterialJumble add(FMaterial material){
        domain.add(material);
        materials.add(material);
        return this;
    }

    @Override
    public FMaterial select(Random random) {
        return materials.get(random.nextInt(materials.size()));
    }

    @Override
    public Set<FMaterial> domain() {
        return domain;
    }
}
