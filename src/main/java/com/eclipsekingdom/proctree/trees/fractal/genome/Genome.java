package com.eclipsekingdom.proctree.trees.fractal.genome;

public enum Genome {
    OAK_BIG, OAK_MEDIUM, OAK_SMALL, OAK_MASSIVE
    ;
    public GenomeBase getGenome(){
        switch (this){
            case OAK_SMALL: return new OakSmallGenome();
            case OAK_MEDIUM: return new OakMediumGenome();
            case OAK_BIG: return new OakBigGenome();
            case OAK_MASSIVE: return new OakMassiveGenome();
            default: return new OakBigGenome();
        }
    }
}
