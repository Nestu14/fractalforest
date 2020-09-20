package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.Scale;

public interface IGenome {

    IClumpGene getClump();
    ISplitGene getSplit();
    IAngleGene getAngle();
    IDecayGene getDecay();
    ITrunkGene getTrunk();
    ILeafGene getLeaf();
    IRootGene getRoot();

}
