package com.eclipsekingdom.fractalforest.util.theme.material;

import com.eclipsekingdom.fractalforest.util.X.FMaterial;

import java.util.Random;
import java.util.Set;

public interface IMaterialFactory {
    FMaterial select(Random random);

    Set<FMaterial> domain();
}
