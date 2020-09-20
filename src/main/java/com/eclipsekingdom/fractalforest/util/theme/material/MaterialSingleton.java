package com.eclipsekingdom.fractalforest.util.theme.material;

import com.eclipsekingdom.fractalforest.util.X.FMaterial;

import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class MaterialSingleton implements IMaterialFactory {

    private FMaterial material;

    public MaterialSingleton(FMaterial material){
        this.material = material;
    }

    @Override
    public FMaterial select(Random random) {
        return material;
    }

    @Override
    public Set<FMaterial> domain() {
        return Collections.singleton(material);
    }

}
