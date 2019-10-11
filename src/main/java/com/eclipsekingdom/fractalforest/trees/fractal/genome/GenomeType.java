package com.eclipsekingdom.fractalforest.trees.fractal.genome;

public enum GenomeType {
    OAK, BUCK_EYE, MAGNOLIA, ELM, BIRCH, WEIRWOOD
    ;
    public Genome value(){
        switch (this){
            case MAGNOLIA: return new MagnoliaGenome();
            case BUCK_EYE: return new BuckEyeGenome();
            case OAK: return new OakGenome();
            case BIRCH: return new BirchGenome();
            case ELM: return new ElmGenome();
            case WEIRWOOD: return new WeirwoodGenome();
            default: return new OakGenome();
        }
    }
}
