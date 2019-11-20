package com.eclipsekingdom.fractalforest.trees;

public interface ITree {

    void growPhased(int phaseTicks);

    void growInstant();

    String getSpecies();

}
