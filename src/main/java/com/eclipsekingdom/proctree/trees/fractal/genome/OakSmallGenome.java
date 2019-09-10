package com.eclipsekingdom.proctree.trees.fractal.genome;

import com.eclipsekingdom.proctree.functions.Exponential;
import com.eclipsekingdom.proctree.trees.fractal.genome.gene.*;
import com.eclipsekingdom.proctree.util.range.Bounds;

public class OakSmallGenome extends GenomeBase {

    public OakSmallGenome() {
        super(new ClumpGene(0),
                new SplitGene(3,4),
                new AngleGene(new Bounds(Math.PI/4, Math.PI /2)),
                new DecayGene(new Bounds(0.22,0.66)),
                new TrunkGene(new Bounds(3,4), new Bounds(0.5,0.5), new Bounds(-0.5,0.5)),
                new LeafGene(1, 3),
                new RootGene(0,2,new Exponential(1,1.2), new Bounds(0.4,0.5),new Bounds(1,2)));
    }

}
