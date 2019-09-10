package com.eclipsekingdom.proctree.trees.fractal.genome;

import com.eclipsekingdom.proctree.functions.Exponential;
import com.eclipsekingdom.proctree.trees.fractal.genome.gene.*;
import com.eclipsekingdom.proctree.util.range.Bounds;

public class OakMediumGenome extends GenomeBase{

    public OakMediumGenome() {
        super(new ClumpGene(0),
                new SplitGene(3,5),
                new AngleGene(new Bounds(Math.PI /4, Math.PI /2)),
                new DecayGene(new Bounds(0.22,0.66)),
                new TrunkGene(new Bounds(4,6), new Bounds(0.8,1), new Bounds(-0.7,0.7)),
                new LeafGene(1, 2.2),
                new RootGene(1,3,new Exponential(1,1.2), new Bounds(0.4,0.5),new Bounds(2,3)));
    }

}
