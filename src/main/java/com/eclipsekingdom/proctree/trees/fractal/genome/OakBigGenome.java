package com.eclipsekingdom.proctree.trees.fractal.genome;

import com.eclipsekingdom.proctree.functions.Exponential;
import com.eclipsekingdom.proctree.trees.fractal.genome.gene.*;
import com.eclipsekingdom.proctree.util.range.Bounds;

public class OakBigGenome extends GenomeBase {
    public OakBigGenome() {
        super(new ClumpGene(0.05),
                new SplitGene(3,4),
                new AngleGene(new Bounds(0, Math.PI/3)),
                new DecayGene(new Bounds(0.23,0.34)),
                new TrunkGene(new Bounds(6,9), new Bounds(1.5,2), new Bounds(-1,1)),
                new LeafGene(1, 2.2),
                new RootGene(4,6,new Exponential(1,1.2), new Bounds(1,1),new Bounds(4,5)));
    }
}
