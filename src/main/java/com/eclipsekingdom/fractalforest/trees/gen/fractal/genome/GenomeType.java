package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type.*;

public enum GenomeType {
    OAK, BUCK_EYE, MAGNOLIA, ELM, BIRCH, WEIRWOOD, FLAME_TREE, WHITE_ASH, BLOOD_BUSH;

    public Genome value() {
        switch (this) {
            case MAGNOLIA:
                return new MagnoliaGenome();
            case BUCK_EYE:
                return new BuckEyeGenome();
            case OAK:
                return new OakGenome();
            case BIRCH:
                return new BirchGenome();
            case ELM:
                return new ElmGenome();
            case WEIRWOOD:
                return new WeirwoodGenome();
            case FLAME_TREE:
                return new FlameTreeGenome();
            case WHITE_ASH:
                return new WhiteAshGenome();
            case BLOOD_BUSH:
                return new BloodBush();
            default:
                return new OakGenome();
        }
    }
}
