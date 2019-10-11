package com.eclipsekingdom.fractalforest.trees.fractal.genome;

import com.eclipsekingdom.fractalforest.trees.fractal.genome.gene.*;

public interface IGenome {

    IClumpGene getClump();
    ISplitGene getSplit();
    IAngleGene getAngle();
    IDecayGene getDecay();
    ITrunkGene getTrunk();
    ILeafGene getLeaf();
    IRootGene getRoot();

}
