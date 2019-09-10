package com.eclipsekingdom.proctree.trees.fractal.genome;

import com.eclipsekingdom.proctree.trees.fractal.genome.gene.*;

public interface IGenome {

    IClumpGene getClump();
    ISplitGene getSplit();
    IAngleGene getAngle();
    IDecayGene getDecay();
    ITrunkGene getTrunk();
    ILeafGene getLeaf();
    IRootGene getRoot();

}
