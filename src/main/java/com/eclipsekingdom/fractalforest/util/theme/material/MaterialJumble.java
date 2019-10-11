package com.eclipsekingdom.fractalforest.util.theme.material;

import org.bukkit.Material;

import java.util.*;

public class MaterialJumble implements IMaterialFactory {

    private List<Material> materials = new ArrayList<>();
    private Set<Material> domain = new HashSet<>();

    public MaterialJumble add(Material material){
        domain.add(material);
        materials.add(material);
        return this;
    }

    @Override
    public Material select(Random random) {
        return materials.get(random.nextInt(materials.size()));
    }

    @Override
    public Set<Material> domain() {
        return domain;
    }
}
