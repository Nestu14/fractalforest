package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type.*;

public enum GenomeType {
    OAK, BUCK_EYE, MAGNOLIA, ELM, BIRCH, WEIRWOOD, FLAME_TREE, WHITE_ASH, NARROW_LEAF_ASH, BLOOD_BUSH, CYGNI, SECCHI, SHANTUNG_MAPLE, HICKORY,
    ;

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
            case NARROW_LEAF_ASH:
                return new NarrowLeafAshGenome();
            case BLOOD_BUSH:
                return new BloodBush();
            case CYGNI:
                return new CygniGenome();
            case SECCHI:
                return new SecchiGenome();
            case SHANTUNG_MAPLE:
                return new ShantungMapleGenome();
            case HICKORY:
                return new HickoryGenome();
            default:
                return new OakGenome();
        }
    }
}
